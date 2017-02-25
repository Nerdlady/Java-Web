package services;

import models.User;
import dtos.UserDto;
import org.modelmapper.ModelMapper;
import repositories.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public void save(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(modelMapper, User.class);
        this.userRepository.save(user);
    }

    @Override
    public UserDto getByUsername(String username) {
        User user = this.userRepository.getByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = null;
        if (user != null){
            userDto = modelMapper.map(user,UserDto.class);
        }
        return userDto;
    }
}
