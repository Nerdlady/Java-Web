package com.cardealer.services;

import com.cardealer.models.bindingModels.user.LoggedUser;
import com.cardealer.models.bindingModels.user.LoginUser;
import com.cardealer.models.bindingModels.user.RegisterUser;

public interface UserService {

    void persist(RegisterUser registerUser);

    LoggedUser getByUsernameAndPassword(String username,String password);

    LoginUser getByUsername(String username);
}
