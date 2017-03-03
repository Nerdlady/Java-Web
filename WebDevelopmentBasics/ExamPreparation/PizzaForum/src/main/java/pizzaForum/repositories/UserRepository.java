package pizzaForum.repositories;

import pizzaForum.entities.User;

public interface UserRepository {
    void save(User user);

    long getUsersCount();

    User findByUsernameOrEmailAndPassword(String usernameOrEmail, String password);

    User getById(Long id);

}
