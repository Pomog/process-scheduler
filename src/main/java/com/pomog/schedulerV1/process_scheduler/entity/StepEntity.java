package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "step")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class StepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int ID;
    
    @Column(name = "step-name")
    @NonNull
    private String stepName;
    
    @OneToMany(
            mappedBy = "equipmentEntities",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<EquipmentEntity> equipmentEntities;
    
    @OneToOne
    @JoinColumn(name = "code")
    private RoomEntity roomEntity;
    
    @OneToMany(
            mappedBy = "operator",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<OperatorEntity> operatorEntities; // Operators certified for this stage
    
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
