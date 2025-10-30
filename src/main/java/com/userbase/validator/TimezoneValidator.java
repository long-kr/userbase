package com.userbase.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.ZoneId;
import java.util.TimeZone;

public class TimezoneValidator implements ConstraintValidator<ValidTimezone, String> {
    
    @Override
    public void initialize(ValidTimezone constraintAnnotation) {
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