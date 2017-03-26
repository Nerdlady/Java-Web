package com.residentEvil.validaters;

import com.residentEvil.annotations.IsInTheFuture;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class IsInTheFutureValidator implements ConstraintValidator<IsInTheFuture,Date>{
    @Override
    public void initialize(IsInTheFuture isInTheFuture) {

    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        boolean isInTheFuture = false;
        if(date == null){
            return isInTheFuture;
        }

        Date currentDate = new Date();
        isInTheFuture= date.after(currentDate);
        return isInTheFuture;
    }
}
