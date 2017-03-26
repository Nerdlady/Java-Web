package com.residentEvil.services;

import com.residentEvil.entities.Role;
import com.residentEvil.entities.User;
import com.residentEvil.models.bindlingModels.EditUserModel;
import com.residentEvil.models.bindlingModels.RegisterUserModel;
import com.residentEvil.models.bindlingModels.RoleModel;
import com.residentEvil.models.viewModels.UserModel;
import com.residentEvil.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void persist(RegisterUserModel userModel) {
        User user = this.modelMapper.map(userModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userModel.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserModel> getAll() {
        List<User> users = this.userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        for (User user : users) {
            UserModel userModel = this.modelMapper.map(user, UserModel.class);
            userModels.add(userModel);
        }
        return userModels;
    }

    @Override
    public EditUserModel getById(Long id) {
        User user = this.userRepository.findOne(id);
        EditUserModel editUserModel = null;
        if (user != null) {
            editUserModel = this.modelMapper.map(user, EditUserModel.class);
        }
        return editUserModel;
    }

    @Override
    public void edit(EditUserModel editUserModel) {
        User user = this.userRepository.findOne(editUserModel.getId());
        Set<Role> roles = new HashSet<>();
        Set<RoleModel> rolesModels = this.roleService.getAllByName(editUserModel.getAuthorities());
        for (RoleModel roleModel : rolesModels) {
            Role role = this.modelMapper.map(roleModel, Role.class);
            roles.add(role);
        }
        user.setAuthorities(roles);
        this.userRepository.saveAndFlush(user);
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
