package com.pomog.schedulerV1.process_scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class SkillDTO {
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String processName;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String stepName;
    
    private int level;
    
    public SkillDTO(SkillEntity skillEntity){
        this.processName = skillEntity.getProcessName();
        this.stepName = skillEntity.getStepName();
        this.level = skillEntity.getLevel();
    }
}
