package com.gameOfNerds.areas.user.validations;

import com.gameOfNerds.areas.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsEmailRegisteredValidator implements ConstraintValidator<IsEmailRegister,String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(IsEmailRegister isEmailRegister) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userService.isEmailRegistered(s);
    }
}
