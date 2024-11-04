package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
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
    private UUID ID = UUID.randomUUID(); // Generate UUID when an instance is created
    
    @Column(name = "step-name")
    @NonNull
    private String stepName;
    
    @ManyToOne
    @JoinColumn(name = "process_id")
    private ProcessEntity processEntity;
    
    @ManyToMany(mappedBy = "stepEntities", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EquipmentEntity> equipmentEntity;
    
    @OneToOne
    @JoinColumn(name = "code")
    private RoomEntity roomEntity;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "step_operator",
            joinColumns = @JoinColumn(name = "step_id"),
            inverseJoinColumns = @JoinColumn(name = "operator_id")
    )
    private List<OperatorEntity> operatorEntities;
    
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
}
