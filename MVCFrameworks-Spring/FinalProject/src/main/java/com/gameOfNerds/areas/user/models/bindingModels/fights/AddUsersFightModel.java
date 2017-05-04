package com.gameOfNerds.areas.user.models.bindingModels.fights;

import com.gameOfNerds.areas.question.models.bindingModels.QuestionModel;
import com.gameOfNerds.areas.user.models.bindingModels.UserModel;

public class AddUsersFightModel {
    private UserModel challenger;
    private UserModel challenged;
    private QuestionModel question;
    private Boolean isChallengerUserAnswered;
    private Boolean isChallengedUserAnswered;

    public UserModel getChallenger() {
        return challenger;
    }

    public void setChallenger(UserModel challenger) {
        this.challenger = challenger;
    }


    public UserModel getChallenged() {
        return challenged;
    }

    public void setChallenged(UserModel challenged) {
        this.challenged = challenged;
    }


    public QuestionModel getQuestion() {
        return question;
    }

    public void setQuestion(QuestionModel question) {
        this.question = question;
    }

    public Boolean getIsChallengerUserAnswered() {
        return isChallengerUserAnswered;
    }

    public void setIsChallengerUserAnswered(Boolean challengerUserAnswered) {
        isChallengerUserAnswered = challengerUserAnswered;
    }

    public Boolean getIsChallengedUserAnswered() {
        return isChallengedUserAnswered;
    }

    public void setIsChallengedUserAnswered(Boolean challengedUserAnswered) {
        isChallengedUserAnswered = challengedUserAnswered;
    }


}
