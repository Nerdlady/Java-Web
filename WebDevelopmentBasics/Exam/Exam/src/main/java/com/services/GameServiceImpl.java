package com.services;

import com.entities.Game;
import com.models.bindingModels.game.EditGame;
import com.models.bindingModels.game.NewGame;
import com.models.viewModels.game.AdminGameView;
import com.models.viewModels.game.GameDetailsView;
import com.models.viewModels.game.GameView;
import com.models.viewModels.game.ModifiableGameView;
import com.repositories.GameRepository;
import com.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class GameServiceImpl implements GameService {
    @Inject
    private GameRepository gameRepository;

    @Override
    public void save(NewGame game) {
        Game gameToPersist = ModelParser.getInstance().map(game,Game.class);
        this.gameRepository.save(gameToPersist);
    }

    @Override
    public List<AdminGameView> getAllAdminGames() {
        List<Game> games = this.gameRepository.getAllGames();
        List<AdminGameView> adminGameViews = new ArrayList<>();
        for (Game game : games) {
            AdminGameView adminGameView = ModelParser.getInstance().map(game,AdminGameView.class);
            adminGameViews.add(adminGameView);
        }
        return adminGameViews;
    }

    @Override
    public void update(EditGame editGame) {
        Game game = ModelParser.getInstance().map(editGame,Game.class);
        this.gameRepository.update(game);
    }

    @Override
    public ModifiableGameView getModifiableGameById(Long id) {
        Game game = this.gameRepository.getGameById(id);
        ModifiableGameView modifiableGameView = null;
        if (game != null){
            modifiableGameView = ModelParser.getInstance().map(game,ModifiableGameView.class);
        }
        return modifiableGameView;
    }

    @Override
    public void delete(Long id) {
        this.gameRepository.delete(id);
    }

    @Override
    public List<GameView> getAllHomeGames() {
        List<Game> games = this.gameRepository.getAllGames();
        List<GameView> homeGameViews = new ArrayList<>();
        for (Game game : games) {
            GameView homeGameView = ModelParser.getInstance().map(game,GameView.class);
            homeGameViews.add(homeGameView);
        }
        return homeGameViews;
    }

    @Override
    public GameDetailsView getDetailsGameById(Long id) {
        Game game = this.gameRepository.getGameById(id);
        GameDetailsView gameDetailsView = null;
        if (game != null){
            gameDetailsView = ModelParser.getInstance().map(game,GameDetailsView.class);
        }
        return gameDetailsView;
    }

    @Override
    public EditGame getByIdToEdit(Long id) {
        Game game = this.gameRepository.getGameById(id);
        EditGame editGame = null;
        if (game != null){
            editGame = ModelParser.getInstance().map(game,EditGame.class);
        }
        return editGame;
    }

    @Override
    public GameView getGameViewById(Long id) {
        Game game = this.gameRepository.getGameById(id);
        GameView homeGameView = null;
        if (game != null){
            homeGameView = ModelParser.getInstance().map(game,GameView.class);
        }
        return homeGameView;
    }
}
