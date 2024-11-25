package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import org.springframework.stereotype.Component;

@Component
public class ProcessDTOFactory implements DTOFactory<ProcessEntity, ProcessDTO>{
    @Override
    public ProcessDTO createFromEntity(ProcessEntity entity) {
        return new ProcessDTO(entity);
    }
}
