package com.models.bindingModels.user;

import com.constants.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUser {
    @NotNull(message = Constants.EMAIL_NOT_NULL)
    @Pattern(regexp = ".+@.*\\.*", message = Constants.INVALID_EMAIL)
    private String email;

    @NotNull(message = Constants.FULL_NAME_NOT_NULL)
    private String fullName;

    @NotNull(message = Constants.PASSWORD_NOT_NULL)
    @Size(min = 6, message = Constants.INVALID_PASSWORD_LENGTH)
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*", message = Constants.INVALID_PASSWORD)
    private String password;

    @NotNull(message = Constants.CONFIRM_PASSWORD_NOT_NULL)
    private String confirmPassword;

    public RegisterUser() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
