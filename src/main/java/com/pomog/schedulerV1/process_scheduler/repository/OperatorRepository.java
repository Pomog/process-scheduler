package com.pomog.schedulerV1.process_scheduler.repository;

import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperatorRepository extends JpaRepository<OperatorEntity, UUID> {

    // https://www.bezkoder.com/jpa-many-to-many/
    Optional<OperatorEntity> findOperatorEntityByNameIgnoreCase(String name);

    void deleteOperatorEntityByName(String name);

    List<OperatorEntity> findOperatorEntitiesByPrefersNight(boolean preferNights);

    List<OperatorEntity> findOperatorEntitiesByPrefersWeekend(boolean preferWeekends);

    List<OperatorEntity> findOperatorEntitiesBySkillEntities_ID(UUID skillUUID);

    List<OperatorEntity> findOperatorEntitiesBySkillEntities_ProcessNameAndSkillEntities_StepName(String processName, String stepName);
    
    List<OperatorEntity> findOperatorEntitiesBySkillEntities_ProcessName(String processName);
}
