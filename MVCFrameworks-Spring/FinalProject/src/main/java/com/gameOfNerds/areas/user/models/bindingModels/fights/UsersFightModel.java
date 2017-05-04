package com.gameOfNerds.areas.user.models.bindingModels.fights;

import com.gameOfNerds.areas.user.models.bindingModels.UserModel;

public class UsersFightModel {
    private UserModel currentUser;
    private UserModel otherUser;

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }


    public UserModel getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(UserModel otherUser) {
        this.otherUser = otherUser;
    }

}
