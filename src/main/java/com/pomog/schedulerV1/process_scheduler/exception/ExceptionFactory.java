package com.pomog.schedulerV1.process_scheduler.exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionFactory {
    
    public ResourceNotFoundException notFoundException(String entityName, String identifier) {
        String message = String.format("%s with %s not found", entityName, identifier);
        return new ResourceNotFoundException(message);
    }
    
    public ResourceNotFoundException notFoundException(String entityName) {
        String message = String.format("%s not found", entityName);
        return new ResourceNotFoundException(message);
    }
    
    public IllegalArgumentException createUnsupportedShiftTypeException(String shiftType) {
        String message = String.format("Unsupported shift type: %s", shiftType);
        return new IllegalArgumentException(message);
    }
    
    public IllegalArgumentException createErrorAbsentIDorEmail() {
        String message = "Either 'id' or 'name' must be provided.";
        return new IllegalArgumentException(message);
    }
}
