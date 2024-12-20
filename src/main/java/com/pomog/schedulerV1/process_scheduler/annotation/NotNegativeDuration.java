package com.pomog.schedulerV1.process_scheduler.annotation;

import com.pomog.schedulerV1.process_scheduler.validation.NotNegativeDurationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotNegativeDurationValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNegativeDuration {
    String message() default "Duration must not be negative";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
