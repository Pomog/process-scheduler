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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID ID;
    
    @Column(name = "name")
    @NonNull
    private String name;
    
    @OneToOne
    @JoinColumn(name = "name")
    private SettingsEntity settingsEntity;
    
    @Column(name = "prefers_night")
    private boolean prefersNight;
    
    @Column(name = "prefers_wekend")
    private boolean prefersWeekend;
    
    @OneToMany(
            mappedBy = "skill",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<SkillEntity> skillEntities;
}
