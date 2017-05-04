package com.gameOfNerds.areas.clan.models.viewModels;

import com.gameOfNerds.areas.user.models.bindingModels.UserModel;
import com.gameOfNerds.areas.user.models.viewModels.UserGameInfoViewModel;

import java.util.Set;

public class ClanViewModel {
    private Long id;
    private String name;
    private Double totalScore;
    private Set<UserGameInfoViewModel> users;
    private UserModel owner;

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

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Set<UserGameInfoViewModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserGameInfoViewModel> users) {
        this.users = users;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}
