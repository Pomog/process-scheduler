package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.DTOFactory;
import com.pomog.schedulerV1.process_scheduler.dto.ProcessDTO;
import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProcessServiceImpl extends BaseService<ProcessEntity, ProcessDTO> implements ProcessService {
    
    private final ExceptionFactory exceptionFactory;
    protected ProcessServiceImpl(
            ResponseFactory responseFactory,
            MessageSource messageSource,
            DTOFactory<ProcessEntity,
            ProcessDTO> dtoFactory,
            ExceptionFactory exceptionFactory) {
        super(responseFactory, messageSource, dtoFactory, exceptionFactory);
        this.exceptionFactory = exceptionFactory;
    }
    
    @Override
    public Response<ProcessEntity> createProcessResponse(ProcessEntity processEntity) {
        return null;
    }
    
    @Override
    public Response<ProcessEntity> getProcessByIdResponse(UUID processId) {
        return null;
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
