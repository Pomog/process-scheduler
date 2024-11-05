package com.pomog.schedulerV1.process_scheduler.rest;

import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.service.StepService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.pomog.schedulerV1.process_scheduler.response.Response;

@RestController
@RequestMapping("/step")
public class StepController {
    private final StepService stepService;
    
    public StepController(StepService stepService) {
        this.stepService = stepService;
    }
    
    @PostMapping
    public Response<StepDTO> saveStep (@Valid @RequestBody StepDTO stepDTO) {
        StepEntity stepEntity = new StepEntity();
        System.out.println(stepEntity);
        
        return stepService.saveStep(stepEntity);
    }
    
    @GetMapping
    public void saveStep2 () {
        
        System.out.println("TestTestTestTestTestTestTestTestTestTest");

    }
}
