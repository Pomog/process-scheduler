package com.pomog.schedulerV1.process_scheduler.repository;

import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SettingsRepository extends CrudRepository<SettingsEntity, UUID> {
}
