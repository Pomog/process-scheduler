package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import response.Response;

public interface StepService {
    Response<StepDTO> saveStep (StepEntity stepEntity);
}
