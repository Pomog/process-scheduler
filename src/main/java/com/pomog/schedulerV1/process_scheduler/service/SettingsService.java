package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;

import java.util.List;
import java.util.UUID;

public interface SettingsService {
    // save operation
    Response<SettingsDTO> saveSettings(SettingsEntity settings);
    
    // read operation
    Response<List<SettingsDTO>> fetchSettingsList();
    
    // find
    Response<SettingsDTO> findById(UUID settingsId);
    
    // update operation
    SettingsEntity updateSettings(SettingsEntity settings, UUID settingsId);
    
    // delete operation
    void deleteSettingsById(UUID settingsId);

}
