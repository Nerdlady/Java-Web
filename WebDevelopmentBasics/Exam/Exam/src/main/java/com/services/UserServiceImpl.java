package com.services;


import com.entities.Game;
import com.entities.User;
import com.entities.enums.Role;
import com.models.bindingModels.user.LoggedUser;
import com.models.bindingModels.user.RegisterUser;
import com.models.viewModels.game.GameView;
import com.models.viewModels.user.UserView;
import com.repositories.UserRepository;
import com.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;

    @Override
    public void save(RegisterUser user) {
        User userToPersist = ModelParser.getInstance().map(user,User.class);
        long usersCount = this.userRepository.getUsersCount();
        if (usersCount == 0){
            userToPersist.setRole(Role.ADMIN);
        } else {
            userToPersist.setRole(Role.USER);
        }
        this.userRepository.save(userToPersist);
    }

    @Override
    public LoggedUser getByEmailAndPassword(String email, String password) {
        User user = this.userRepository.findByEmailAndPassword(email,password);
        LoggedUser loggedUser = null;
        if (user != null){
            loggedUser = ModelParser.getInstance().map(user,LoggedUser.class);
        }
        return loggedUser;
    }

    @Override
    public UserView getById(Long id) {
        User user = this.userRepository.getById(id);
        UserView userView = null;
        if (user != null){
            userView = ModelParser.getInstance().map(user,UserView.class);
        }
        return userView;
    }

    @Override
    public List<GameView> getUserCartGames(Long userId) {
        User user = this.userRepository.getById(userId);
        Set<Game> games = user.getCartGames();
        List<GameView> gameViews = new ArrayList<>();
        for (Game game : games) {
            GameView gameView = ModelParser.getInstance().map(game,GameView.class);
            gameViews.add(gameView);
        }
        return gameViews;
    }

    @Override
    public List<GameView> getUserBoughtGames(Long userId) {
        User user = this.userRepository.getById(userId);
        Set<Game> games = user.getBoughtGames();
        List<GameView> gameViews = new ArrayList<>();
        for (Game game : games) {
            GameView gameView = ModelParser.getInstance().map(game,GameView.class);
            gameViews.add(gameView);
        }
        return gameViews;
    }

}
