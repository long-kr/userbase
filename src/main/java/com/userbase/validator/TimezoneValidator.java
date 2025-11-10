package com.userbase.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimezoneValidatorImpl.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TimezoneValidator {
    String message() default "Invalid timezone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}