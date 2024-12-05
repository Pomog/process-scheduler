package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.annotation.ValidUUID;
import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class SkillDTO {
    
    @ValidUUID
    private UUID ID;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String processName;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String stepName;
    
    private List<OperatorDTO> operators = new ArrayList<>();
    
    private int level;
    
    // Learning EVE
    public SkillDTO(SkillEntity skillEntity) {
        this.ID = skillEntity.getID();
        this.processName = skillEntity.getProcessName();
        this.stepName = skillEntity.getStepName();
        this.level = skillEntity.getLevel();
        this.operators = skillEntity.getOperatorEntities().stream()
                .map(OperatorDTO::new)
                .collect(Collectors.toList());
        toDtoWithoutOperators();
    }
    
    public void toDtoWithoutOperators() {
        this.operators.forEach((operator) -> operator.setSkills(null));
    }
    
}
