package com.repositories;

import com.entities.Game;

import java.util.List;

public interface GameRepository {
    void save(Game game);
    List<Game> getAllGames();
    Game getGameById(Long id);
    void update(Game game);
    void delete(Long id);
}
