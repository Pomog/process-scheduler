package com.pomog.schedulerV1.process_scheduler.repository;

import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SettingsRepository extends CrudRepository<SettingsEntity, UUID> {
    @Query("SELECT s FROM SettingsEntity s WHERE EXISTS " +
            "(SELECT o FROM OperatorEntity o WHERE o.name = :operatorName AND o.settingsEntity = s)")
    Optional<SettingsEntity> findSettingsByOperatorName(@Param("operatorName") String operatorName);
}

