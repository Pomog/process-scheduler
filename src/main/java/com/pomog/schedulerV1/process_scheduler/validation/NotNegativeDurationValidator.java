package com.pomog.schedulerV1.process_scheduler.validation;

import com.pomog.schedulerV1.process_scheduler.annotation.NotNegativeDuration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Duration;

public class NotNegativeDurationValidator implements ConstraintValidator<NotNegativeDuration, Duration> {
    @Override
    public boolean isValid(Duration duration, ConstraintValidatorContext context) {
        return duration == null || !duration.isNegative();
    }
}
