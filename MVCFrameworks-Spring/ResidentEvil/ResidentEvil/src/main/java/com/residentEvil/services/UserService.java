package com.residentEvil.services;

import com.residentEvil.models.bindlingModels.EditUserModel;
import com.residentEvil.models.bindlingModels.RegisterUserModel;
import com.residentEvil.models.viewModels.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void persist(RegisterUserModel userModel);
    List<UserModel> getAll();
    EditUserModel getById(Long id);
    void  edit(EditUserModel editUserModel);
}
