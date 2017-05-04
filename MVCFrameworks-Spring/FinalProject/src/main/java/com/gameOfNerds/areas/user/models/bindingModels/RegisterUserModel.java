package com.gameOfNerds.areas.user.models.bindingModels;


import com.gameOfNerds.areas.user.validations.IsEmailRegister;
import com.gameOfNerds.areas.user.validations.IsPasswordMatching;
import com.gameOfNerds.areas.user.validations.IsUsernameTaken;
import com.gameOfNerds.constants.Constants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@IsPasswordMatching
public class RegisterUserModel {
    @Size(min =4,max = 20,message = Constants.USERNAME_LENGTH)
    @IsUsernameTaken
    private String username;


    @Size(min = 4,max = 30,message = Constants.PASSWORD_LENGTH)
    private String password;

    private String confirmPassword;


    @Size(min = 3,message = Constants.FULL_NAME_LENGTH)
    private String fullName;

    @IsEmailRegister
    @NotEmpty(message = Constants.ENTER_VALID_EMAIL)
    @Email(message = Constants.ENTER_VALID_EMAIL)
    private String email;

    private String imageURL;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
