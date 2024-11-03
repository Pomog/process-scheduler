package com.pomog.schedulerV1.process_scheduler.dto;


import com.pomog.schedulerV1.process_scheduler.annotation.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ProcessDTO {
    
    @ValidUUID
    private UUID ID;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String processName;
    
    private List<StepDTO> steps;
    
    public ProcessDTO(ProcessEntity processEntity) {
        this.ID = processEntity.getID();
        this.processName = processEntity.getProcessName();
        this.steps = processEntity.getStepEntities().stream()
                .map(StepDTO::new)
                .collect(Collectors.toList());
    }
}
