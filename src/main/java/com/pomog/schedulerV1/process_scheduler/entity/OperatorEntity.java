package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "operator", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class OperatorEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID ID = UUID.randomUUID(); // Generate UUID when an instance is created
    
    @Column(name = "name")
    @NonNull
    private String name;
    
    @OneToOne
    @JoinColumn(name = "settings")
    private SettingsEntity settingsEntity;
    
    @Column(name = "prefers_night")
    private boolean prefersNight;
    
    @Column(name = "prefers_wekend")
    private boolean prefersWeekend;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "operator_skill",
            joinColumns = @JoinColumn(name = "operator_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<SkillEntity> skillEntities;
}
