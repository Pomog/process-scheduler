package com.pomog.schedulerV1.process_scheduler.rest;

import com.pomog.schedulerV1.process_scheduler.service.StepService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StepController {
    private final StepService stepService;
    
    public StepController(StepService stepService) {
        this.stepService = stepService;
    }
    
    
}
