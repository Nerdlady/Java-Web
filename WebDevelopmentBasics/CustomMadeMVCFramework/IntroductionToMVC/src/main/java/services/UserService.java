package services;

import dtos.UserDto;

public interface UserService {
    void save(UserDto userDto);
    UserDto getByUsername(String username);
}
