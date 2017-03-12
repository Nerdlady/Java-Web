package com.repositories;


import com.entities.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserRepository.class)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public long getUsersCount() {
        Query query = this.entityManager.createQuery("SELECT COUNT(u) FROM User AS u");
        return (long) query.getSingleResult();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email AND u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        return (User) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public User getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.id =:id");
        query.setParameter("id",id);

        return (User) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void removeUserGame(Long userId, Long gameId) {

    }
}
