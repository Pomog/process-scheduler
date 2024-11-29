package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.DTOFactory;
import com.pomog.schedulerV1.process_scheduler.dto.ProcessDTO;
import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.repository.ProcessRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import io.micrometer.common.util.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProcessServiceImpl extends BaseService<ProcessEntity, ProcessDTO> implements ProcessService {
    private final ProcessRepository processRepository;
    private final ResponseFactory responseFactory;
    
    protected ProcessServiceImpl(
            ResponseFactory responseFactory,
            MessageSource messageSource,
            DTOFactory<ProcessEntity,
                    ProcessDTO> dtoFactory,
            ProcessRepository processRepository, ExceptionFactory exceptionFactory, ResponseFactory responseFactory1) {
        super(responseFactory, messageSource, dtoFactory, exceptionFactory);
        this.processRepository = processRepository;
        this.responseFactory = responseFactory1;
    }
    
    @Override
    public Response<ProcessDTO> createProcessResponse(ProcessEntity processEntity) {
        return buildSuccessResponseToSave(convertToDTO(processRepository.save(processEntity)));
    }
    
    @Override
    public Response<ProcessDTO> getProcessByIdResponse(UUID processId) {
        return buildSuccessResponseToGet(
                convertToDTO(processRepository.findById(processId)
                        .orElseThrow(() -> buildNotFoundException("Process"))));
    }
    
    @Override
    public Response<List<ProcessDTO>> getAllProcesses() {
        
        return buildResponseForList(convertEntitiesToDTOs(processRepository.findAll()));
    }
    
    @Override
    public Response<ProcessDTO> updateProcessResponse(UUID processId, ProcessEntity updatedProcess) {
        
        ProcessEntity processFromDB = processRepository.findById(processId)
                .orElseThrow(() -> buildNotFoundException("ProcessEntity"));
        
        updateProcessName(processFromDB, updatedProcess);
        
        updateProcessSteps(processFromDB, updatedProcess);
        
        return buildSuccessResponseToSave(convertToDTO(processFromDB));
    }
    
    private static void updateProcessName(ProcessEntity processFromDB, ProcessEntity updatedProcess) {
        String newProcessName = updatedProcess.getProcessName();
        // newProcessName can not be null - class constructor constraint
        if (StringUtils.isNotBlank(newProcessName) && !newProcessName.equals(processFromDB.getProcessName())){
            processFromDB.setProcessName(newProcessName);
        }
    }
    
    private static void updateProcessSteps(ProcessEntity processFromDB, ProcessEntity updatedProcess) {
        List<StepEntity> updatedSteps = updatedProcess.getStepEntities();
        
        if (updatedSteps == null || updatedSteps.isEmpty()) {
            return;
        }
        
        List<StepEntity> stepsFromDB = processFromDB.getStepEntities();
        Set<StepEntity> stepsFromDBSet = new HashSet<>(stepsFromDB);
        
        updatedProcess.getStepEntities().forEach(
                (step) -> {
                    if (!stepsFromDBSet.contains(step)){
                        step.setProcessEntity(processFromDB);
                        processFromDB.addStep(step);
                    } else {
                        stepsFromDBSet.remove(step);
                    }
                }
        );
        
        // TODO not sure should the other steps be deleted
        // stepsFromDBSet.forEach(processFromDB::deleteStep);
    }

    
    @Override
    public Response<Void> deleteProcessById(UUID processId) {
        processRepository.deleteById(processId);
        return buildSuccessResponseToDelete();
    }
    
    // I understand that this method should be in StepService.
    // Only for a learning purpose
    @Override
    public Response<List<StepDTO>> getStepsForProcessResponse(UUID processId) {
        List<StepEntity> stepEntities = processRepository.findStepEntitiesByProcessId(processId);
        
        if (stepEntities.isEmpty()) {
            return responseFactory.buildSuccessResponse(
                    getMessage("fetch.empty"),
                    Collections.emptyList()
            );
        }
        
        List<StepDTO> stepDTOs = stepEntities.stream()
                .map(StepDTO::new)
                .toList();
        
        return responseFactory.buildSuccessResponse(getMessage("fetch.success"), stepDTOs);
    }
}
