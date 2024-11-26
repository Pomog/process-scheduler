package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID = UUID.randomUUID(); // Generate UUID when an instance is created
    
    @Column(name = "process-name")
    @NonNull
    @Size(min = 2, max = 30)
    private String processName;
    
    @OneToMany(
            mappedBy = "processEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<StepEntity> stepEntities = new ArrayList<>();
    
    public boolean addStep (StepEntity stepEntity){
       return stepEntities.add(stepEntity);
    }
    
    public boolean deleteStep (StepEntity stepEntity){
        return stepEntities.remove(stepEntity);
    }
}
