package com.pomog.schedulerV1.process_scheduler.controller;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.service.OperatorService;
import com.pomog.schedulerV1.process_scheduler.validation.ParametersValidator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@RestController
@RequestMapping("/operator")
public class OperatorController {
    private final OperatorService operatorService;
    private final ExceptionFactory exceptionFactory;
    private final Map<String, Function<Object, Response<Void>>> deleteHandlers;
    private final ParametersValidator parametersValidator;
    
    public OperatorController(OperatorService operatorService, ExceptionFactory exceptionFactory, ParametersValidator parametersValidator) {
        this.operatorService = operatorService;
        this.exceptionFactory = exceptionFactory;
        
        this.deleteHandlers = Map.of(
                "id", (param) -> operatorService.getResponseForDeleteOperatorByID((UUID) param),
                "name", (param) -> operatorService.getResponseForDeleteOperatorByName((String) param)
        );
        this.parametersValidator = parametersValidator;
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
    
    @GetMapping("/all")
    public Response<List<OperatorDTO>> getAllOperators () {
        return operatorService.getResponseForAllOperators();
    }
    
    @DeleteMapping("/")
    public Response<Void> deleteOperatorByIDOrName(@RequestParam Map<String, String> params) {
        parametersValidator.idAndNameValidator(params);
        
        return params.entrySet().stream()
                .filter(entry -> deleteHandlers.containsKey(entry.getKey()))
                .findFirst()
                .map(entry -> deleteHandlers.get(entry.getKey()).apply(entry.getValue()))
                .orElseThrow(exceptionFactory::createErrorAbsentIDorEmail);
    }

}
