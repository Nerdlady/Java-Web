package com.gameOfNerds.areas.clan.models.bindingModels;

import com.gameOfNerds.areas.user.models.bindingModels.UserModel;

public class ClanModel {
    private Long id;
    private String name;
    private UserModel owner;
    private Double totalScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }
}
