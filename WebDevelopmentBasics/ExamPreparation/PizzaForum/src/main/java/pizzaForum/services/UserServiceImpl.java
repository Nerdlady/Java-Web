package pizzaForum.services;

import pizzaForum.entities.User;
import pizzaForum.entities.enums.Role;
import pizzaForum.models.bindingModels.user.LoggedUser;
import pizzaForum.models.bindingModels.user.RegisterUser;
import pizzaForum.models.viewModels.UserView;
import pizzaForum.repositories.UserRepository;
import pizzaForum.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;

    @Override
    public void save(RegisterUser user) {
        User userToPersist = ModelParser.getInstance().map(user,User.class);
        long usersCount = this.userRepository.getUsersCount();
        if (usersCount == 0){
            userToPersist.setRole(Role.ADMIN);
        } else {
            userToPersist.setRole(Role.USER);
        }
        this.userRepository.save(userToPersist);
    }

    @Override
    public LoggedUser getByUsernameOrEmailAndPassword(String usernameOrEmail, String password) {
        User user = this.userRepository.findByUsernameOrEmailAndPassword(usernameOrEmail,password);
        LoggedUser loggedUser = null;
        if (user != null){
            loggedUser = ModelParser.getInstance().map(user,LoggedUser.class);
        }
        return loggedUser;
    }

    @Override
    public UserView getById(Long id) {
        User user = this.userRepository.getById(id);
        UserView userView = null;
        if (user != null){
            userView = ModelParser.getInstance().map(user,UserView.class);
        }
        return userView;
    }
}
