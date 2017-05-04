package com.gameOfNerds.areas.user.models.bindingModels.fights;

import com.gameOfNerds.areas.question.models.bindingModels.QuestionModel;
import com.gameOfNerds.areas.user.models.bindingModels.UserModel;

public class EditUsersFightModel {
    private Long id;
    private QuestionModel question;
    private UserModel challenger;
    private UserModel challenged;
    private Boolean isChallengerUserAnswered;
    private Boolean isChallengedUserAnswered;
    private Boolean isChallengerAnsweredCorrect;
    private Boolean isChallengedAnsweredCorrect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setIsChallengerUserAnswered(Boolean challengerUserAnswered) {
        isChallengerUserAnswered = challengerUserAnswered;
    }

    public Boolean getIsChallengedUserAnswered() {
        return isChallengedUserAnswered;
    }

    public void setIsChallengedUserAnswered(Boolean challengedUserAnswered) {
        isChallengedUserAnswered = challengedUserAnswered;
    }

    public Boolean getIsChallengerAnsweredCorrect() {
        return isChallengerAnsweredCorrect;
    }

    public void setIsChallengerAnsweredCorrect(Boolean challengerAnsweredCorrect) {
        isChallengerAnsweredCorrect = challengerAnsweredCorrect;
    }

    public Boolean getIsChallengedAnsweredCorrect() {
        return isChallengedAnsweredCorrect;
    }

    public void setIsChallengedAnsweredCorrect(Boolean challengedAnsweredCorrect) {
        isChallengedAnsweredCorrect = challengedAnsweredCorrect;
    }
}
