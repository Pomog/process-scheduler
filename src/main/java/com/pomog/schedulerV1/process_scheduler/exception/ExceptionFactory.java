package com.pomog.schedulerV1.process_scheduler.exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionFactory {
    
    public ResourceNotFoundException createNotFoundException(String entityName, String identifier) {
        String message = String.format("%s with %s not found", entityName, identifier);
        return new ResourceNotFoundException(message);
    }
}
