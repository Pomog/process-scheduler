package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "skill", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"process_name", "step_name"})
})
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class SkillEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;
    
    @Column(name = "process_name")
    @NonNull
    private String processName;
    
    @Column(name = "step_name")
    @NonNull
    private String stepName;
    
    @Column(name = "level")
    private int level;
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "skillEntities")
    @JsonIgnore // is used to ignore the logical property used in serialization and deserialization
    private List<OperatorEntity> operatorEntities = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillEntity that)) return false;
        if (!super.equals(o)) return false;
        
        if (!getProcessName().equals(that.getProcessName())) return false;
        return getStepName().equals(that.getStepName());
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getProcessName().hashCode();
        result = 31 * result + getStepName().hashCode();
        return result;
    }
}
