package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import org.springframework.stereotype.Service;
import response.Response;

@Service
public class StepServiceImpl extends BaseService<StepDTO> implements StepService{
    @Override
    public Response<StepDTO> saveStep(StepEntity stepEntity) {
        return generateResponse(200, "Step saved", new StepDTO(stepEntity));
    }
}
