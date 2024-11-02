package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.entity.EquipmentEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EquipmentDTO {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    
    @NotBlank(message = "Code cannot be blank")
    @Pattern(regexp = "[A-Z]{3}-\\d{4}", message = "Code format must be 'AAA-1234'")
    private String code;
    
    private List<StepDTO> steps = new ArrayList<>();
    
    public EquipmentDTO(EquipmentEntity equipmentEntity) {
        this.name = equipmentEntity.getName();
        this.code = equipmentEntity.getCode();
        this.steps.addAll(equipmentEntity.getStepEntities().stream()
                .map(StepDTO::new)
                .toList());
    }
}
