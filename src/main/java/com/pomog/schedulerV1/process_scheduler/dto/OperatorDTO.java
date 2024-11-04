package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.annotation.ValidUUID;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class OperatorDTO {
    
    @ValidUUID
    private UUID ID;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    
    private SettingsDTO settings;
    private boolean prefersNight;
    private boolean prefersWeekend;
    private List<SkillDTO> skills;
    
    public OperatorDTO(OperatorEntity operatorEntity) {
        this.ID = operatorEntity.getID();
        this.name = operatorEntity.getName();
        this.settings = new SettingsDTO(operatorEntity.getSettingsEntity());
        this.skills = operatorEntity.getSkillEntities().stream()
                .map(SkillDTO::new)
                .collect(Collectors.toList());
        
    }
    
}
