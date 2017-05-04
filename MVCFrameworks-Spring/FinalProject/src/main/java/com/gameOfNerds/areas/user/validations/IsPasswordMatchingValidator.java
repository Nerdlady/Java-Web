package com.gameOfNerds.areas.user.validations;

import com.gameOfNerds.areas.user.models.bindingModels.RegisterUserModel;
import com.gameOfNerds.areas.user.models.bindingModels.UpdateUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching,Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof RegisterUserModel){
            RegisterUserModel userModel = (RegisterUserModel) object;
            return userModel.getPassword().equals(userModel.getConfirmPassword());
        }

        if (object instanceof UpdateUser){
            UpdateUser userModel = (UpdateUser) object;
            return userModel.getPassword().equals(userModel.getConfirmPassword());
        }
        return false;
    }
}
