package pizzaForum.services;

import pizzaForum.models.bindingModels.user.LoggedUser;
import pizzaForum.models.bindingModels.user.RegisterUser;
import pizzaForum.models.viewModels.UserView;

public interface UserService {
    void save(RegisterUser user);
    LoggedUser getByUsernameOrEmailAndPassword(String usernameOrEmail,String password);
    UserView getById(Long id);
}
