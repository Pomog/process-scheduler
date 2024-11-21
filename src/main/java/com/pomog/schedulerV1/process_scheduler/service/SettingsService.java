package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;

import java.util.List;
import java.util.UUID;

public interface SettingsService {
    // save operation
    Response<SettingsDTO> getResponseSave(SettingsEntity settings);
    
    // read operation
    Response<List<SettingsDTO>> getResponseFetchAll();
    
    // find
    Response<SettingsDTO> getResponseFindById(UUID settingsId);
    
    // update operation
    Response<SettingsDTO> getResponseUpdate(SettingsEntity settings, UUID settingsId);
    
    // delete operation
    void getResponseDeleteById(UUID settingsId);

}
