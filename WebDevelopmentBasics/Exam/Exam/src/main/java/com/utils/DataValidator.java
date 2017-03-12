package com.utils;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.LinkedHashSet;
import java.util.Set;

public class DataValidator {

    public static <T> Set<String> validateData(T bindingModel) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(bindingModel);

        Set<String> errors = new LinkedHashSet<>();
        for (ConstraintViolation<T> constraint : constraintViolations) {
            errors.add(constraint.getMessage());
        }
        return errors;
    }
}
