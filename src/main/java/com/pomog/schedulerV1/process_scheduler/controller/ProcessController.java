package com.pomog.schedulerV1.process_scheduler.controller;

import com.pomog.schedulerV1.process_scheduler.dto.ProcessDTO;
import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.service.ProcessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/process")
public class ProcessController {
    private final ProcessService processService;
    
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }
    @PostMapping
    public Response<ProcessDTO> saveProcess (@Valid @RequestBody ProcessEntity processEntity){
        return processService.saveProcess(processEntity);
    }
    
    @GetMapping("/{id}")
    public Response<ProcessDTO> getProcessByID(@Valid @PathVariable UUID id){
        return processService.getProcessByID(id);
    }

}
