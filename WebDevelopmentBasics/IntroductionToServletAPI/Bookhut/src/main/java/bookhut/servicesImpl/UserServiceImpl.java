package bookhut.servicesImpl;

import bookhut.entities.User;
import bookhut.models.binding.LoginModel;
import bookhut.repositories.UserRepository;
import bookhut.repositoriesImpl.UserRepositoryImpl;
import bookhut.services.UserService;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = UserRepositoryImpl.getInstance();
    }

    @Override
    public void createUser(LoginModel loginModel) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(loginModel,User.class);
        this.userRepository.createUser(user);
    }

    @Override
    public LoginModel findByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username,password);
        ModelMapper modelMapper = new ModelMapper();
        LoginModel loginModel = null;
        if (user != null){
            loginModel = modelMapper.map(user,LoginModel.class);
        }
        return loginModel;
    }
}
