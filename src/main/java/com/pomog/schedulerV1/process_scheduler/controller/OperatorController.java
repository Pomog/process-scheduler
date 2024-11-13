package com.pomog.schedulerV1.process_scheduler.controller;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.service.OperatorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorController {
    private final OperatorService operatorService;
    
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }
    
    @PostMapping
    public Response<OperatorDTO> saveOperator(@Valid @RequestBody OperatorEntity operatorEntity) {
        return operatorService.getResponseSaveOperator(operatorEntity);
    }
    
    /**
     * Retrieves an operator by name.
     * @param name the name of the operator to retrieve
     * @return the response containing the operator data, and timeStamp
     * @throws ResourceNotFoundException if no operator with the given name is found
     */
    @GetMapping("/{operatorName}")
    public Response<OperatorDTO> findOperatorByName(@PathVariable String operatorName){
        return operatorService.getResponseFetchOperatorByName(operatorName);
    }
    
    @GetMapping("/night-shift/{prefersNightShift}")
    public Response<List<OperatorDTO>> getOperatorsByNightShiftPreference(@PathVariable boolean prefersNightShift){
        return operatorService.getResponseForOperatorsPreferringNightShift(prefersNightShift);
    }
    
    @GetMapping("/weekend-shift/{prefersWeekendShift}")
    public Response<List<OperatorDTO>> getOperatorsByWeekendShiftPreference(@PathVariable boolean prefersWeekendShift){
        return operatorService.getResponseForOperatorsPreferringWeekendShift (prefersWeekendShift);
    }
    
}
