package com.pomog.schedulerV1.process_scheduler.repository;

import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, UUID> {
    
    @Query("SELECT s FROM SkillEntity s WHERE s.level = :level")
    List<SkillEntity> findSkillsByLevel(@Param("level") int level);
    Optional<SkillEntity> findByProcessNameAndStepName(String processName, String stepName);
    
    List<SkillEntity> findAllByOperatorEntities_Name(String operatorName);

}
