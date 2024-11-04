package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "equipment")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class EquipmentEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID ID = UUID.randomUUID(); // Generate UUID when an instance is created
    
    @Column(name = "name")
    private String name;
    
    @NonNull
    @Column(name = "code")
    private String code;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "equipment_steps",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "step_id")
    )
    private List<StepEntity> stepEntities;
}
