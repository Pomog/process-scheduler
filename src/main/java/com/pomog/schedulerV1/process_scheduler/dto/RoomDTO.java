package com.pomog.schedulerV1.process_scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.pomog.schedulerV1.process_scheduler.entity.RoomEntity;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    
    @NotBlank(message = "Code cannot be blank")
    @Pattern(regexp = "[A-Z]{3}-\\d{4}", message = "Code format must be 'AAA-1234'")
    private String code;
    
    public RoomDTO(RoomEntity roomEntity){
        this.code = roomEntity.getCode();
    }
}
