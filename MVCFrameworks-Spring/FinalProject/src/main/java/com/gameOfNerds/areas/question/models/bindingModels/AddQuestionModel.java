package com.gameOfNerds.areas.question.models.bindingModels;

import com.gameOfNerds.constants.Constants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class AddQuestionModel {
    @Size(min = 10, message = Constants.QUESTION_CONTEXT_LENGTH)
    private String context;

    @NotEmpty(message = Constants.ANSWER_NOT_EMPTY)
    private String rightAnswer;

    @NotEmpty(message = Constants.ANSWER_NOT_EMPTY)
    private String wrongAnswerOne;

    @NotEmpty(message = Constants.ANSWER_NOT_EMPTY)
    private String wrongAnswerTwo;

    @NotEmpty(message = Constants.ANSWER_NOT_EMPTY)
    private String wrongAnswerThree;

    private Boolean isApprove;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getWrongAnswerOne() {
        return wrongAnswerOne;
    }

    public void setWrongAnswerOne(String wrongAnswerOne) {
        this.wrongAnswerOne = wrongAnswerOne;
    }

    public String getWrongAnswerTwo() {
        return wrongAnswerTwo;
    }

    public void setWrongAnswerTwo(String wrongAnswerTwo) {
        this.wrongAnswerTwo = wrongAnswerTwo;
    }

    public String getWrongAnswerThree() {
        return wrongAnswerThree;
    }

    public void setWrongAnswerThree(String wrongAnswerThree) {
        this.wrongAnswerThree = wrongAnswerThree;
    }

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean approve) {
        isApprove = approve;
    }
}
