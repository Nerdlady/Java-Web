package com.gameOfNerds.areas.user.models.bindingModels;

import com.gameOfNerds.areas.clan.models.bindingModels.ClanModel;

public class UserGameInfoModel {
    private UserModel user;
    private ClanModel clan;
    private Double score;

    public UserGameInfoModel() {
    }

    public UserGameInfoModel(UserModel user, Double score) {
        this.user = user;
        this.score = score;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ClanModel getClan() {
        return clan;
    }

    public void setClan(ClanModel clan) {
        this.clan = clan;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
