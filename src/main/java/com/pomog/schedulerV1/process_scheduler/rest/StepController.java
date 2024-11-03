package com.pomog.schedulerV1.process_scheduler.rest;

import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.service.StepService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.Response;

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
}
