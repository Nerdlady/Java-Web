package com.gameOfNerds.areas.question.services;

import com.gameOfNerds.areas.question.entities.Question;
import com.gameOfNerds.areas.question.models.bindingModels.AddQuestionModel;
import com.gameOfNerds.areas.question.models.bindingModels.EditQuestionModel;
import com.gameOfNerds.areas.question.models.viewModels.QuestionViewModel;
import com.gameOfNerds.areas.question.repositories.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    private Random random;

    private  List<Question> questions;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
        this.questions = new LinkedList<>();
        this.random = new Random();
    }

    @Override
    public void persist(AddQuestionModel question) {
        Question questionToPersist = this.modelMapper.map(question, Question.class);
        this.questionRepository.saveAndFlush(questionToPersist);
    }

    @Override
    public void update(EditQuestionModel editQuestionModel) {
        Question question = this.modelMapper.map(editQuestionModel, Question.class);
        this.questionRepository.saveAndFlush(question);
    }

    @Override
    public void delete(Long id) {
        this.questionRepository.delete(id);
    }

    @Override
    public List<QuestionViewModel> getAllApproveQuestions() {
        List<Question> questions = this.questionRepository.findAllByIsApproveOrderByIdDesc(true);
        List<QuestionViewModel> questionViewModels = new ArrayList<>();
        for (Question question : questions) {
            QuestionViewModel questionViewModel = this.modelMapper.map(question, QuestionViewModel.class);
            questionViewModels.add(questionViewModel);
        }
        return questionViewModels;
    }

    @Override
    public List<QuestionViewModel> getAllNotApproveQuestions() {
        List<Question> questions = this.questionRepository.findAllByIsApproveOrderByIdDesc(false);
        List<QuestionViewModel> questionViewModels = new ArrayList<>();
        for (Question question : questions) {
            QuestionViewModel questionViewModel = this.modelMapper.map(question, QuestionViewModel.class);
            questionViewModels.add(questionViewModel);
        }
        return questionViewModels;
    }

    @Override
    public QuestionViewModel getOneQuestion() {
        int index = random.nextInt(this.questions.size()-1);
        Question question = this.questions.get(index);
        QuestionViewModel questionViewModel = this.modelMapper.map(question,QuestionViewModel.class);
        return questionViewModel;
    }

    @Override
    public QuestionViewModel getOneQuestionById(Long id) {
        Question question = this.questionRepository.findOne(id);
        QuestionViewModel questionViewModel = this.modelMapper.map(question,QuestionViewModel.class);
        return questionViewModel;
    }

    @Override
    public void refreshQuestions() {
        this.questions = this.questionRepository.findAll();
    }


}
