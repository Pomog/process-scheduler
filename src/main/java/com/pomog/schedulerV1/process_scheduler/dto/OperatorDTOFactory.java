package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import org.springframework.stereotype.Component;

@Component
public class OperatorDTOFactory implements DTOFactory<OperatorEntity, OperatorDTO> {
    @Override
    public OperatorDTO createFromEntity(OperatorEntity entity) {
        return new OperatorDTO(entity);
    }
}
