package com.gameOfNerds.areas.user.services;

import com.gameOfNerds.areas.user.models.bindingModels.fights.AddUsersFightModel;
import com.gameOfNerds.areas.user.models.bindingModels.fights.EditUsersFightModel;
import com.gameOfNerds.areas.user.models.bindingModels.fights.UsersFightModel;
import com.gameOfNerds.areas.user.models.viewModels.UsersFightViewModel;

import java.util.List;

public interface UsersFightService {
    boolean isUsersInFight(UsersFightModel usersFightModel);
    boolean isCurrentUserAnswerInCurrentFight(UsersFightModel usersFightModel);
    EditUsersFightModel save(AddUsersFightModel usersFightModel);
    EditUsersFightModel getCurrentFight(UsersFightModel usersFightModel);
    void update(EditUsersFightModel editUsersFightModel);
    int getChallengedChallengesCount(Long userId);
    int getChallengerChallengesCount(Long userId);
    List<UsersFightViewModel> getChallengerFightsByUsername(String username);
    List<UsersFightViewModel> getChallengedFightsByUsername(String username);

}
