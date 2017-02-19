package bookhut.services;

import bookhut.models.binding.LoginModel;

public interface UserService {
    void createUser(LoginModel loginModel);

    LoginModel findByUsernameAndPassword(String username, String password);
}
