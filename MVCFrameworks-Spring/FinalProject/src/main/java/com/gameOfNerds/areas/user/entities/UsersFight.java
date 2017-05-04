package com.gameOfNerds.areas.user.entities;

import com.gameOfNerds.areas.question.entities.Question;

import javax.persistence.*;

@Entity
@Table(name = "users_fights")
public class UsersFight {
    private Long id;
    private User challenger;
    private User challenged;
    private Question question;
    private Boolean isChallengerUserAnswered;
    private Boolean isChallengedUserAnswered;
    private Boolean isChallengerAnsweredCorrect;
    private Boolean isChallengedAnsweredCorrect;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "challenger_id",referencedColumnName = "id")
    public User getChallenger() {
        return challenger;
    }

    public void setChallenger(User challenger) {
        this.challenger = challenger;
    }

    @ManyToOne
    @JoinColumn(name = "challenged_id",referencedColumnName = "id")
    public User getChallenged() {
        return challenged;
    }

    public void setChallenged(User challenged) {
        this.challenged = challenged;
    }

    @ManyToOne
    @JoinColumn(name = "question_id",referencedColumnName = "id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
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
