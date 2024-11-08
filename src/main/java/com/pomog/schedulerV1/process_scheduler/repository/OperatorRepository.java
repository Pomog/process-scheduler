package com.pomog.schedulerV1.process_scheduler.repository;

import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, UUID> {
    
    // Find an operator by name
    Optional<OperatorEntity> findByName(String name);
    
    // Find all operators that prefer night shifts
    List<OperatorEntity> findByPrefersNightTrue();
    
    // Find all operators that prefer weekends
    List<OperatorEntity> findByPrefersWeekendTrue();
    
    // Find all operators with a specific skill level for a given skill
    List<OperatorEntity> findBySkillEntities_ID(UUID skillUUID);
    
    // Find all operators with a specific skill by process name and step name
    List<OperatorEntity> findBySkillEntities_ProcessNameAndSkillEntities_StepName(String processName, String stepName);
    
    @Query("SELECT o FROM OperatorEntity o JOIN o.skillEntities s WHERE s.processName = :name")
    List<SkillEntity> findBySkills_ProcessName(@Param("level") String name);
}
