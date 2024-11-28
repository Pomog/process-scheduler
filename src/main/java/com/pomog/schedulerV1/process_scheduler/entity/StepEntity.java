package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "step")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class StepEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID; // Generate UUID when an instance is created
    
    @Column(name = "step-name")
    @NonNull
    private String stepName;
    
    @ManyToOne
    @JoinColumn(name = "process_id")
    private ProcessEntity processEntity;
    
    @ManyToMany(mappedBy = "stepEntities", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EquipmentEntity> equipmentEntities = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "code")
    private RoomEntity roomEntity;
    
    @Column(name = "duration")
    private Duration duration;
    
    @Column(name = "is_night_shift")
    private boolean isNightShift;
    
    @Column(name = "is_weekend")
    private boolean isWeekend;
    
    @Column(name = "start-time")
    private LocalDateTime startTime;
    
    @Column(name = "end-time")
    private LocalDateTime endTime;
    
    public boolean addEquipment (EquipmentEntity equipmentEntity){
        return equipmentEntities.add(equipmentEntity);
    }
    
    public boolean deleteEquipment (EquipmentEntity equipmentEntity){
        return equipmentEntities.remove(equipmentEntity);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StepEntity that)) return false;
        
        if (isNightShift() != that.isNightShift()) return false;
        if (isWeekend() != that.isWeekend()) return false;
        if (!getStepName().equals(that.getStepName())) return false;
        if (getProcessEntity() != null ? !getProcessEntity().equals(that.getProcessEntity()) : that.getProcessEntity() != null)
            return false;
        if (getEquipmentEntities() != null ? !getEquipmentEntities().equals(that.getEquipmentEntities()) : that.getEquipmentEntities() != null)
            return false;
        if (getRoomEntity() != null ? !getRoomEntity().equals(that.getRoomEntity()) : that.getRoomEntity() != null)
            return false;
        if (getDuration() != null ? !getDuration().equals(that.getDuration()) : that.getDuration() != null)
            return false;
        if (getStartTime() != null ? !getStartTime().equals(that.getStartTime()) : that.getStartTime() != null)
            return false;
        return getEndTime() != null ? getEndTime().equals(that.getEndTime()) : that.getEndTime() == null;
    }
    
    @Override
    public int hashCode() {
        int result = getStepName().hashCode();
        result = 31 * result + (getProcessEntity() != null ? getProcessEntity().hashCode() : 0);
        result = 31 * result + (getEquipmentEntities() != null ? getEquipmentEntities().hashCode() : 0);
        result = 31 * result + (getRoomEntity() != null ? getRoomEntity().hashCode() : 0);
        result = 31 * result + (getDuration() != null ? getDuration().hashCode() : 0);
        result = 31 * result + (isNightShift() ? 1 : 0);
        result = 31 * result + (isWeekend() ? 1 : 0);
        result = 31 * result + (getStartTime() != null ? getStartTime().hashCode() : 0);
        result = 31 * result + (getEndTime() != null ? getEndTime().hashCode() : 0);
        return result;
    }
}
