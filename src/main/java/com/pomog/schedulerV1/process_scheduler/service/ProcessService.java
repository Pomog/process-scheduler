package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.ProcessDTO;
import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;

import java.util.List;
import java.util.UUID;

public interface ProcessService {
    Response<ProcessDTO> createProcessResponse(ProcessEntity processEntity);
    
    Response<ProcessDTO> getProcessByIdResponse(UUID processId);
    
    Response<List<ProcessDTO>> getAllProcesses();
    
    Response<ProcessDTO> updateProcessResponse(UUID processId, ProcessEntity updatedProcess);
    
    Response<Void> deleteProcessById(UUID processId);
    
    Response<List<StepDTO>> getStepsForProcessResponse(UUID processId);
    
    Response<ProcessDTO> addStepToProcess(UUID processId, StepEntity stepEntity);
}
