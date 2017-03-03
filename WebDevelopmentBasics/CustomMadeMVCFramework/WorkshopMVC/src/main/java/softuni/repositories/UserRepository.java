package softuni.repositories;

import softuni.models.User;

public interface UserRepository {
    void save(User user);
    User getUserByEmail(String email);
}
