package com.gameOfNerds.areas.clan.models.bindingModels;

import com.gameOfNerds.areas.user.models.bindingModels.UserModel;
import com.gameOfNerds.constants.Constants;

import javax.validation.constraints.Size;

public class CreateClanModel {
    @Size(min = 4,message = Constants.CLAN_NAME_LENGTH)
    private String name;
    private UserModel owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}
