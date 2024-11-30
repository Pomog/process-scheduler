package com.pomog.schedulerV1.process_scheduler.repository;

import com.pomog.schedulerV1.process_scheduler.entity.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StepRepository extends JpaRepository<StepEntity, UUID> {
}
