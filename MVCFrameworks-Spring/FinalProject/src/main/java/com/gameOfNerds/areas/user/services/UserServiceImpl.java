package com.gameOfNerds.areas.user.services;

import com.gameOfNerds.areas.user.entities.Role;
import com.gameOfNerds.areas.user.entities.User;
import com.gameOfNerds.areas.user.models.bindingModels.*;
import com.gameOfNerds.areas.user.models.viewModels.UserViewModel;
import com.gameOfNerds.areas.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public UserModel persist(RegisterUserModel userModel) {
        User user = this.modelMapper.map(userModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userModel.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        RoleModel roleModel = this.roleService.findByAuthority("ROLE_USER");
        Role role  = this.modelMapper.map(roleModel, Role.class);
        user.addRole(role);

        user = this.userRepository.saveAndFlush(user);
        UserModel userModelToReturn = this.modelMapper.map(user,UserModel.class);
        return userModelToReturn;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        User user = this.userRepository.findOneByUsername(username);
        return user != null;
    }

    @Override
    public boolean isEmailRegistered(String email) {
        User user = this.userRepository.findOneByEmail(email);
        return user != null;
    }

    @Override
    public UserViewModel getById(Long id) {
        User user = this.userRepository.findOne(id);
        UserViewModel userViewModel = null;
        if (user != null){
            userViewModel = this.modelMapper.map(user,UserViewModel.class);
        }
        return userViewModel;
    }

    @Override
    public UserModel findById(Long id) {
        User user = this.userRepository.findOne(id);
        UserModel userModel = null;
        if (user != null){
            userModel = this.modelMapper.map(user,UserModel.class);
        }
        return userModel;
    }

    @Override
    public UserModel findByUsername(String username) {
        User user = this.userRepository.findOneByUsername(username);
        UserModel userModel = null;
        if (user != null){
            userModel = this.modelMapper.map(user,UserModel.class);
        }
        return userModel;
    }

    @Override
    public UserInfoModel getUserInfoById(Long id) {
        User user = this.userRepository.findOne(id);
        UserInfoModel userInfoModel = null;
        if (user != null){
            userInfoModel = this.modelMapper.map(user,UserInfoModel.class);
        }
        return userInfoModel;
    }

    @Override
    public void update(UpdateUser updateUser) {
        User user = this.userRepository.findOne(updateUser.getId());
        if (updateUser.getImageURL() != null){
            user.setImageURL(updateUser.getImageURL());
        }

        if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()){
            user.setPassword(this.bCryptPasswordEncoder.encode(updateUser.getPassword()));
        }


        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserViewModel getByUsername(String username) {
        User user = this.userRepository.findOneByUsername(username);
        UserViewModel userModel = null;
        if (user != null){
            userModel = this.modelMapper.map(user,UserViewModel.class);
        }
        return userModel;
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong");
        }

        return user;
    }


}
