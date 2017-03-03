package softuni.services;

import org.modelmapper.ModelMapper;
import softuni.dtos.UserDto;
import softuni.models.User;
import softuni.repositories.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public void save(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        this.userRepository.save(user);
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = this.userRepository.getUserByEmail(email);
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = null;
        if (user != null){
            userDto = modelMapper.map(user,UserDto.class);
        }
        return userDto;
    }
}
