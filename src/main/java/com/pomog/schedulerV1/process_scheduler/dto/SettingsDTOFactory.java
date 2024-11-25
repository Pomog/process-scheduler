package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;

import org.springframework.stereotype.Component;

@Component
public class SettingsDTOFactory implements DTOFactory<SettingsEntity, SettingsDTO> {
    @Override
    public SettingsDTO createFromEntity(SettingsEntity entity) {
        return new SettingsDTO(entity);
    }
}