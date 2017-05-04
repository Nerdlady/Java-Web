package com.gameOfNerds.areas.user.services;

import com.gameOfNerds.areas.user.models.bindingModels.UserGameInfoModel;
import com.gameOfNerds.areas.user.models.viewModels.UserGameInfoViewModel;
import com.gameOfNerds.areas.user.models.viewModels.UserWithClanModel;

import java.util.List;

public interface UserGameInfoService {
    void persist(UserGameInfoModel userGameInfoModel);
    List<UserGameInfoViewModel> findAll();
    void givePoints(Long userId,Double points);
    UserGameInfoModel getByUserUsername(String username);
    void setClan(UserGameInfoModel userGameInfoModel);
    UserWithClanModel getWithClanByUserUsername(String username);
    UserGameInfoViewModel getViewByUserId(Long id);
    UserGameInfoViewModel getViewByUserUsername(String username);
}
