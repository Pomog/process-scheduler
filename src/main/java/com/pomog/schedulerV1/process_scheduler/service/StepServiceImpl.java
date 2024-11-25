package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.dto.StepDTOFactory;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.pomog.schedulerV1.process_scheduler.response.Response;

@Service
public class StepServiceImpl extends BaseService<StepEntity, StepDTO> implements StepService{
    
    public StepServiceImpl(
            ResponseFactory responseFactory,
            MessageSource messageSource,
            StepDTOFactory dtoFactory,
            
            ExceptionFactory exceptionFactory) {
        super(responseFactory, messageSource, dtoFactory, exceptionFactory);
    }
    
    @Override
    public Response<StepDTO> saveStep(StepEntity stepEntity) {
        return generateResponse(200, "Step saved", new StepDTO(stepEntity));
    }
}
