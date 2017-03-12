package com.services;


import com.models.bindingModels.user.LoggedUser;
import com.models.bindingModels.user.RegisterUser;
import com.models.viewModels.game.GameView;
import com.models.viewModels.user.UserView;

import java.util.List;

public interface UserService {
    void save(RegisterUser user);
    LoggedUser getByEmailAndPassword(String email, String password);
    UserView getById(Long id);
    List<GameView> getUserCartGames(Long userId);
    List<GameView> getUserBoughtGames(Long userId);
}
