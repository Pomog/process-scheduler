package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import org.springframework.stereotype.Component;

@Component
public class StepDTOFactory implements DTOFactory<StepEntity, StepDTO> {
    
    @Override
    public StepDTO createFromEntity(StepEntity entity) {
        return new StepDTO(entity);
    }
}
