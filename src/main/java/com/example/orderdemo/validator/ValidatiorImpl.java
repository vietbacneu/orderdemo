package com.example.orderdemo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatiorImpl  implements ConstraintValidator<validatorDemo,String> {

    @Override
    public void initialize(validatorDemo constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[a-z0-9_-]{6,12}$");
    }
}
