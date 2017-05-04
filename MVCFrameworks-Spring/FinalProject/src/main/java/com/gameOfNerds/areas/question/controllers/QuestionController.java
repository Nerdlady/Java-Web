package com.gameOfNerds.areas.question.controllers;

import com.gameOfNerds.areas.question.models.bindingModels.AddQuestionModel;
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

@Controller
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
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

        addQuestionModel.setIsApprove(false);
        this.questionService.persist(addQuestionModel);
        return "redirect:/";
    }

    @GetMapping("practice")
    public String getPracticeQuestionsPage(Model model){
        model.addAttribute(Constants.VIEW, Constants.SINGLE_QUESTION_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("get")
    public ResponseEntity<QuestionViewModel> getQuestion(){
        QuestionViewModel questionViewModel = this.questionService.getOneQuestion();
        return new ResponseEntity<QuestionViewModel>(questionViewModel, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<QuestionViewModel> getQuestionById(@PathVariable Long id){
        QuestionViewModel questionViewModel = this.questionService.getOneQuestionById(id);
        return new ResponseEntity<QuestionViewModel>(questionViewModel, HttpStatus.OK);
    }
}
