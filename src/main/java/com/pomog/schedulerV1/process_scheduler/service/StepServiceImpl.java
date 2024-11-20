package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.pomog.schedulerV1.process_scheduler.response.Response;

@Service
public class StepServiceImpl extends BaseService<StepDTO> implements StepService{
    private final ResponseFactory responseFactory;
    private final MessageSource messageSource;
    public StepServiceImpl(ResponseFactory responseFactory, MessageSource messageSource) {
        super(responseFactory, messageSource);
        this.responseFactory = responseFactory;
        this.messageSource = messageSource;
    }
    
    @Override
    public Response<StepDTO> saveStep(StepEntity stepEntity) {
        return generateResponse(200, "Step saved", new StepDTO(stepEntity));
    }
}
