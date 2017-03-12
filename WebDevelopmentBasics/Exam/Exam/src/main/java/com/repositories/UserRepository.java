package com.repositories;


import com.entities.User;

public interface UserRepository {
    void save(User user);

    long getUsersCount();

    User findByEmailAndPassword(String email, String password);

    User getById(Long id);

    void removeUserGame(Long userId,Long gameId);

}
