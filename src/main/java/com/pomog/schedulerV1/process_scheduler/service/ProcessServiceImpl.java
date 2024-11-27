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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
    public Response<ProcessEntity> updateProcessResponse(UUID processId, ProcessEntity updatedProcess) {
        return null;
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
