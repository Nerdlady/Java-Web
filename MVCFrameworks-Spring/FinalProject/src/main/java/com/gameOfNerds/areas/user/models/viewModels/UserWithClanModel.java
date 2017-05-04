package com.gameOfNerds.areas.user.models.viewModels;

import com.gameOfNerds.areas.clan.models.viewModels.ClanViewModel;

public class UserWithClanModel {
    private UserViewModel user;
    private ClanViewModel clan;

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public ClanViewModel getClan() {
        return clan;
    }

    public void setClan(ClanViewModel clan) {
        this.clan = clan;
    }
}
