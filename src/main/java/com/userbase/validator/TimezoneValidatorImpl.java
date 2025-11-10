package com.userbase.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.ZoneId;
import java.util.TimeZone;

public class TimezoneValidatorImpl implements ConstraintValidator<TimezoneValidator, String> {

    @Override
    public void initialize(TimezoneValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String timezone, ConstraintValidatorContext context) {
        if (timezone == null) {
            return true; // Let @NotNull handle null validation
        }

        try {
            TimeZone.getTimeZone(ZoneId.of(timezone));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}