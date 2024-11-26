package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.DTOFactory;
import com.pomog.schedulerV1.process_scheduler.dto.ProcessDTO;
import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.repository.ProcessRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProcessServiceImpl extends BaseService<ProcessEntity, ProcessDTO> implements ProcessService {
    private final ProcessRepository processRepository;

    protected ProcessServiceImpl(
            ResponseFactory responseFactory,
            MessageSource messageSource,
            DTOFactory<ProcessEntity,
            ProcessDTO> dtoFactory,
            ProcessRepository processRepository, ExceptionFactory exceptionFactory) {
        super(responseFactory, messageSource, dtoFactory, exceptionFactory);
        this.processRepository = processRepository;
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
    public Response<List<ProcessEntity>> getAllProcesses() {
        return null;
    }
    
    @Override
    public Response<ProcessEntity> updateProcessResponse(UUID processId, ProcessEntity updatedProcess) {
        return null;
    }
    
    @Override
    public Response<Void> deleteProcessById(UUID processId) {
        return null;
    }
    
    @Override
    public Response<ProcessEntity> addStepToProcessResponse(UUID processId, StepEntity stepEntity) {
        return null;
    }
    
    @Override
    public Response<ProcessEntity> removeStepFromProcessResponse(UUID processId, UUID stepId) {
        return null;
    }
    
    @Override
    public Response<List<StepEntity>> getStepsForProcessResponse(UUID processId) {
        return null;
    }
}
