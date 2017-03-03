package softuni.services;

import softuni.dtos.UserDto;

public interface UserService {
    void save(UserDto userDto);
    UserDto getByEmail(String email);
}
