package bookhut.repositoriesImpl;

import bookhut.entities.User;
import bookhut.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl userRepository;
    private List<User> users;

    private UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    public static UserRepository getInstance(){
        if (userRepository == null){
            userRepository = new UserRepositoryImpl();
        }
        return userRepository;
    }

    @Override
    public void createUser(User user) {
        this.users.add(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = users
                .stream()
                .filter(u ->
                        u.getUsername().equals(username)
                                && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
        return user;
    }
}
