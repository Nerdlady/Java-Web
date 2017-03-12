package com.services;

import com.models.bindingModels.game.EditGame;
import com.models.bindingModels.game.NewGame;
import com.models.viewModels.game.AdminGameView;
import com.models.viewModels.game.GameDetailsView;
import com.models.viewModels.game.GameView;
import com.models.viewModels.game.ModifiableGameView;

import java.util.List;

public interface GameService {
    void save(NewGame game);
    List<AdminGameView> getAllAdminGames();
    void update(EditGame editGame);
    ModifiableGameView getModifiableGameById(Long id);
    void delete(Long id);
    List<GameView> getAllHomeGames();
    GameDetailsView getDetailsGameById(Long id);
    EditGame getByIdToEdit(Long id);
    GameView getGameViewById(Long id);
}
