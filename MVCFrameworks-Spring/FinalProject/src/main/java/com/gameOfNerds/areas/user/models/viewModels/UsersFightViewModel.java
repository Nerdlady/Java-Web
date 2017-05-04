package com.gameOfNerds.areas.user.models.viewModels;

public class UsersFightViewModel {
    private UserViewModel otherUser;
    private String fightResult;

    public UserViewModel getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(UserViewModel otherUser) {
        this.otherUser = otherUser;
    }

    public String getFightResult() {
        return fightResult;
    }

    public void setFightResult(String fightResult) {
        this.fightResult = fightResult;
    }
}
