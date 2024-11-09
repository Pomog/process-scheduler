package com.pomog.schedulerV1.process_scheduler.controller;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.service.OperatorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operator")
public class OperatorController {
    private final OperatorService operatorService;
    
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }
    
    @PostMapping
    public Response<OperatorDTO> saveOperator(@Valid @RequestBody OperatorEntity operatorEntity) {
        return operatorService.saveOperator(operatorEntity);
    }
}
