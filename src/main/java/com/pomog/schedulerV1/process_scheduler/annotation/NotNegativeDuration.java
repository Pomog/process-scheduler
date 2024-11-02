package com.pomog.schedulerV1.process_scheduler.annotation;

import jakarta.validation.Payload;

public @interface NotNegativeDuration {
    String message() default "Duration must not be negative";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
