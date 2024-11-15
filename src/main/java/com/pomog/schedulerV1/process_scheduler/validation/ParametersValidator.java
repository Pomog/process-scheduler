package com.pomog.schedulerV1.process_scheduler.validation;

import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ParametersValidator {
    private final ExceptionFactory exceptionFactory;
    
    public ParametersValidator(ExceptionFactory exceptionFactory) {
        this.exceptionFactory = exceptionFactory;
    }
    
    public void idAndNameValidator (Map<String, String> params){
        if (params.isEmpty() || (!params.containsKey("id") && !params.containsKey("name"))) {
            throw exceptionFactory.createErrorAbsentIDorEmail();
        }
    }
    
}
