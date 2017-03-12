package com.models.bindingModels.user;

import com.constants.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginUser {

    @NotNull(message = Constants.EMAIL_NOT_NULL)
    @Pattern(regexp = ".+@.*\\.*", message = Constants.INVALID_EMAIL)
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
