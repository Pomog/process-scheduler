package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.annotation.NotNegativeDuration;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class StepDTO {
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String stepName;
    
    private RoomDTO room;
    
    @NotNull(message = "Duration cannot be null")
    @NotNegativeDuration(message = "Duration must not be negative")
    
    private Duration duration;
    private boolean isNightShift;
    private boolean isWeekend;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private List<EquipmentDTO> equipment;
    private List<OperatorDTO> operators; // Operators certified for this stage
    
    public StepDTO(StepEntity stepEntity){
        this.stepName = stepEntity.getStepName();
        this.room = new RoomDTO(stepEntity.getRoomEntity());
        this.duration = stepEntity.getDuration();
        this.isNightShift = stepEntity.isNightShift();
        this.isWeekend = stepEntity.isWeekend();
        this.startTime = stepEntity.getStartTime();
        this.endTime = stepEntity.getEndTime();
        
        this.equipment = stepEntity.getEquipmentEntities().stream()
                .map(EquipmentDTO::new).collect(Collectors.toList());
        this.operators = stepEntity.getOperatorEntities().stream()
                .map(OperatorDTO::new)
                .collect(Collectors.toList());
    }
}
