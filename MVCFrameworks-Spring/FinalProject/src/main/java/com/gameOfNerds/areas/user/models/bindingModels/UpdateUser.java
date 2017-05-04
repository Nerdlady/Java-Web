package com.gameOfNerds.areas.user.models.bindingModels;

import com.gameOfNerds.areas.user.validations.IsPasswordMatching;

@IsPasswordMatching
public class UpdateUser {
    private Long id;

    private String password;

    private String confirmPassword;

    private String imageURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
