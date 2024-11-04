package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "process")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ProcessEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID ID = UUID.randomUUID(); // Generate UUID when an instance is created
    
    @Column(name = "process-name")
    @NonNull
    private String processName;
    
    @OneToMany(
            mappedBy = "processEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<StepEntity> stepEntities;
}
