package com.residentEvil.models.bindlingModels;

import com.residentEvil.annotations.IsPasswordMatching;

import javax.validation.constraints.Size;

@IsPasswordMatching
public class RegisterUserModel {
    @Size(min =4,max = 20,message = "Username should be between 4 and 20 symbols")
    private String username;
    @Size(min = 4,max = 30,message = "Password should be between 4 and 30 symbols")
    private String password;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
