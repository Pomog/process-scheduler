package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;

import java.util.List;
import java.util.UUID;

public interface SettingsService {
    // save operation
    Response<SettingsDTO> saveResponse(SettingsEntity settings);
    
    // read operation
    Response<List<SettingsDTO>> fetchAllResponse();
    
    // find
    Response<SettingsDTO> fetchByIdResponse(UUID settingsId);
    
    // update operation
    Response<SettingsDTO> updateResponse(SettingsEntity settings, UUID settingsId);
    
    // delete operation
    Response<Void> deleteByIdResponse(UUID settingsId);

}
