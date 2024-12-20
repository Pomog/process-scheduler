package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.annotation.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class SettingsDTO {
    
    @ValidUUID
    private UUID ID;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    
    private double normalHours;
    private double nightCoefficient;
    private double weekendCoefficient;
    
    public SettingsDTO (SettingsEntity settingsEntity){
        this.ID = settingsEntity.getID();
        this.name = settingsEntity.getName();
        this.normalHours = settingsEntity.getNormalHours();
        this.nightCoefficient = settingsEntity.getNightCoefficient();
        this.weekendCoefficient = settingsEntity.getWeekendCoefficient();
    }
}
