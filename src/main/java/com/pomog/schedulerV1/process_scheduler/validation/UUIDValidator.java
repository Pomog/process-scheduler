package com.pomog.schedulerV1.process_scheduler.validation;

import com.pomog.schedulerV1.process_scheduler.annotation.ValidUUID;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UUIDValidator implements ConstraintValidator<ValidUUID, UUID> {
    @Override
    public void initialize(ValidUUID constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext context) {
        return uuid != null;
    }
    
}
