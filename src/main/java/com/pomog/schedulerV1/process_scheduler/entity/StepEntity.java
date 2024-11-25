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
    
}
