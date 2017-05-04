package com.gameOfNerds.areas.question.services;

import com.gameOfNerds.areas.question.models.bindingModels.AddQuestionModel;
import com.gameOfNerds.areas.question.models.bindingModels.EditQuestionModel;
import com.gameOfNerds.areas.question.models.viewModels.QuestionViewModel;

import java.util.List;

public interface QuestionService {
    void persist(AddQuestionModel question);
    void update(EditQuestionModel editQuestionModel);
    void delete(Long id);
    List<QuestionViewModel> getAllApproveQuestions();
    List<QuestionViewModel> getAllNotApproveQuestions();
    QuestionViewModel getOneQuestion();
    QuestionViewModel getOneQuestionById(Long id);
    void refreshQuestions();
}
