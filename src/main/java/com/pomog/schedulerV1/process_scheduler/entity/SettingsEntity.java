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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID ID;
    
    @Column(name = "name")
    @NonNull
    private String name;
    
    @Column(name = "normal_hours")
    private double normalHours;
    
    @Column(name = "normal_hours")
    private double nightCoefficient;
    
    @Column(name = "normal_hours")
    private double weekendCoefficient;
}
