package pizzaMore.services;

import pizzaMore.models.entities.User;
import pizzaMore.repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void createUser(User user){
        this.userRepository.createUser(user);
    }

    public User findUserByUsernameAndPassword(String username,String password){
        return this.userRepository.findUserByUsernameAndPassword(username,password);
    }
}
