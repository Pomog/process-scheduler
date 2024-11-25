package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;

import java.util.List;
import java.util.UUID;

public interface ProcessService {
    Response<ProcessEntity> createProcessResponse(ProcessEntity processEntity);
    
    Response<ProcessEntity> getProcessByIdResponse(UUID processId);
    
    Response<List<ProcessEntity>> getAllProcesses();
    
    Response<ProcessEntity> updateProcessResponse(UUID processId, ProcessEntity updatedProcess);
    
    Response<Void> deleteProcessById(UUID processId);
    
    Response<ProcessEntity> addStepToProcessResponse(UUID processId, StepEntity stepEntity);
    
    Response<ProcessEntity> removeStepFromProcessResponse(UUID processId, UUID stepId);
    
    Response<List<StepEntity>> getStepsForProcessResponse(UUID processId);
}
