package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
public class SettingsEntity{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID; // Generate UUID when an instance is created
    
    @Column(name = "name")
    @NotBlank
    private String name;
    
    @Column(name = "normal_hours")
    @Positive
    private double normalHours;
    
    @Column(name = "night_coefficient")
    @Positive
    private double nightCoefficient;
    
    @Column(name = "weekend_coefficient")
    @Positive
    private double weekendCoefficient;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SettingsEntity that)) return false;
        if (!super.equals(o)) return false;
        
        if (getID() != null ? !getID().equals(that.getID()) : that.getID() != null) return false;
        return getName().equals(that.getName());
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getID() != null ? getID().hashCode() : 0);
        result = 31 * result + getName().hashCode();
        return result;
    }
}
