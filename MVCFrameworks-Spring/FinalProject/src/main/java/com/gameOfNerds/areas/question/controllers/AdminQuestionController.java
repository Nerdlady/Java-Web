package com.gameOfNerds.areas.question.controllers;

import com.gameOfNerds.areas.question.models.bindingModels.AddQuestionModel;
import com.gameOfNerds.areas.question.models.bindingModels.EditQuestionModel;
import com.gameOfNerds.areas.question.models.viewModels.QuestionViewModel;
import com.gameOfNerds.areas.question.services.QuestionService;
import com.gameOfNerds.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/questions")
public class AdminQuestionController {

    private final QuestionService questionService;

    @Autowired
    public AdminQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("approve")
    public String getApproveQuestionPage(Model model) {
        List<QuestionViewModel> questionViewModels = this.questionService.getAllNotApproveQuestions();
        model.addAttribute(Constants.VIEW, Constants.QUESTION_TABLE_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("approve/data")
    public ResponseEntity<List<QuestionViewModel>> getApproveQuestionsPageData(Model model) {
        List<QuestionViewModel> questionViewModels = this.questionService.getAllNotApproveQuestions();
        return new ResponseEntity<List<QuestionViewModel>>(questionViewModels, HttpStatus.OK);
    }

    @GetMapping("add")
    public String getAddQuestionPage(Model model, @ModelAttribute AddQuestionModel addQuestionModel) {
        model.addAttribute(Constants.VIEW, Constants.ADD_QUESTION_VIEW);
        return Constants.LAYOUT;
    }

    @PostMapping("add")
    public String addQuestion(@Valid @ModelAttribute AddQuestionModel addQuestionModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(Constants.VIEW, Constants.ADD_QUESTION_VIEW);
            return Constants.LAYOUT;
        }

        addQuestionModel.setIsApprove(true);
        this.questionService.persist(addQuestionModel);
        return "redirect:/admin/questions/all";
    }

    @GetMapping("all")
    public String getAllQuestionsPage(Model model) {
        model.addAttribute(Constants.VIEW, Constants.QUESTION_TABLE_VIEW);

        return Constants.LAYOUT;
    }

    @GetMapping("all/data")
    public ResponseEntity<List<QuestionViewModel>> getAllQuestionsPageData() {
        List<QuestionViewModel> questionViewModels = this.questionService.getAllApproveQuestions();
        return new ResponseEntity<List<QuestionViewModel>>(questionViewModels, HttpStatus.OK);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody EditQuestionModel editQuestionModel) {
        editQuestionModel.setIsApprove(true);
        this.questionService.update(editQuestionModel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        this.questionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
