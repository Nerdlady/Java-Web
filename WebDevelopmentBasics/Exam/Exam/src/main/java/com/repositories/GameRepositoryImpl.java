package com.repositories;

import com.entities.Game;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(GameRepository.class)
public class GameRepositoryImpl implements GameRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Game game) {
        this.entityManager.persist(game);
    }

    @Override
    public List<Game> getAllGames() {
        Query query = this.entityManager.createQuery("SELECT g FROM Game AS g");
        List<Game> games = new ArrayList<>();
        if (query.getResultList().size() > 0) {
            games = query.getResultList();
        }
        return games;
    }

    @Override
    public Game getGameById(Long id) {
        Query query = this.entityManager.createQuery("SELECT g FROM Game AS g WHERE g.id = :id");
        query.setParameter("id", id);

        return (Game) query.getResultList().stream().findFirst().orElse(null);

    }

    @Override
    public void update(Game game) {
        this.entityManager.merge(game);
    }

    @Override
    public void delete(Long id) {
        Query query = this.entityManager.createQuery("DELETE FROM Game AS g WHERE g.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
}
