package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "settings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class SettingsEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID ID = UUID.randomUUID(); // Generate UUID when an instance is created
    
    @Column(name = "name")
    @NonNull
    private String name;
    
    @Column(name = "normal_hours")
    private double normalHours;
    
    @Column(name = "night_coefficient")
    private double nightCoefficient;
    
    @Column(name = "weekend_coefficient")
    private double weekendCoefficient;
}
