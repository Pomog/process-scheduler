package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;
    
    @Column(name = "name")
    @NonNull
    private String name;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "settings")
    private SettingsEntity settingsEntity;
    
    @Column(name = "prefers_night")
    private boolean prefersNight;
    
    @Column(name = "prefers_wekend")
    private boolean prefersWeekend;
    
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "operator_skill",
            joinColumns = @JoinColumn(name = "operator_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<SkillEntity> skillEntities = new ArrayList<>();
    
    public boolean addSkill(SkillEntity skillEntity) {
        return this.skillEntities.contains(skillEntity) && addSkillToEntity(skillEntity);
    }
    
    private boolean addSkillToEntity(SkillEntity skillEntity) {
        this.skillEntities.add(skillEntity);
        skillEntity.getOperatorEntities().add(this);
        return true;
    }
    
    public boolean removeSkill(UUID skillID) {
        return getSkillEntityToRemove(skillID)
                .map(this::removeSkillFromEntities)
                .orElse(false);
    }
    
    private Optional<SkillEntity> getSkillEntityToRemove(UUID skillID) {
        return this.skillEntities.stream()
                .filter(skill -> skill.getID().equals(skillID))
                .findFirst();
    }
    
    private boolean removeSkillFromEntities(SkillEntity skillToRemove) {
        this.skillEntities.remove(skillToRemove);
        skillToRemove.getOperatorEntities().remove(this);
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperatorEntity that)) return false;
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
