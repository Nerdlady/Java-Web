package com.gameOfNerds.areas.user.services;

import com.gameOfNerds.areas.user.models.bindingModels.RegisterUserModel;
import com.gameOfNerds.areas.user.models.bindingModels.UpdateUser;
import com.gameOfNerds.areas.user.models.bindingModels.UserInfoModel;
import com.gameOfNerds.areas.user.models.bindingModels.UserModel;
import com.gameOfNerds.areas.user.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserModel persist(RegisterUserModel userModel);
    boolean isUsernameTaken(String username);
    boolean isEmailRegistered(String email);
    UserViewModel getById(Long id);
    UserModel findById(Long id);
    UserModel findByUsername(String username);
    UserInfoModel getUserInfoById(Long id);
    void update(UpdateUser updateUser);
    UserViewModel getByUsername(String username);
}
