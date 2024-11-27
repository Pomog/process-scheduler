package com.pomog.schedulerV1.process_scheduler.repository;

import com.pomog.schedulerV1.process_scheduler.entity.ProcessEntity;
import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessEntity, UUID> {
    @Query("SELECT s FROM StepEntity s WHERE s.processEntity.ID = :processId")
    List<StepEntity> findStepEntitiesByProcessId(@Param("processId") UUID processId);
}
