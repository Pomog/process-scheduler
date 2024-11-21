package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;
import org.springframework.stereotype.Component;

@Component
public class SkillDTOFactory implements DTOFactory<SkillEntity, SkillDTO> {
    @Override
    public SkillDTO createFromEntity(SkillEntity entity) {
        return new SkillDTO(entity);
    }
    
    
}
