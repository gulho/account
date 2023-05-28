package ee.gulho.assignment.account.utils;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.Calendar;

@Documented
@Constraint(validatedBy = AllowedCurrenciesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedCurrencies {
    String message() default "Include not allowed currencies";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}