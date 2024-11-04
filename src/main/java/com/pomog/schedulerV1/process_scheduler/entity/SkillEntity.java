package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "skill")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class SkillEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID ID = UUID.randomUUID(); // Generate UUID when an instance is created
    
    @Column(name = "process_name")
    @NonNull
    private String processName;
    
    @Column(name = "step_name")
    @NonNull
    private String stepName;
    
    @Column(name = "level")
    private int level;
    
    @ManyToMany(mappedBy = "skillEntities", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OperatorEntity> operatorEntities;
}