package com.cardealer.services;

import com.cardealer.entities.User;
import com.cardealer.models.bindingModels.user.LoggedUser;
import com.cardealer.models.bindingModels.user.LoginUser;
import com.cardealer.models.bindingModels.user.RegisterUser;
import com.cardealer.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void persist(RegisterUser registerUser) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(registerUser,User.class);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public LoggedUser getByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username,password);
        ModelMapper modelMapper = new ModelMapper();
        LoggedUser loggedUser = null;
        if (user!=null) {
            loggedUser = modelMapper.map(user,LoggedUser.class);
        }

        return loggedUser;
    }

    @Override
    public LoginUser getByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        LoginUser loginUser = null;
        if (user != null){
            loginUser = modelMapper.map(user, LoginUser.class);
    }
        return loginUser;
    }
}
