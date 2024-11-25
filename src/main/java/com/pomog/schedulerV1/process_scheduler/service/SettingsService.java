package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;

import java.util.List;
import java.util.UUID;

public interface SettingsService {

    Response<SettingsDTO> saveResponse(SettingsEntity settings);

    Response<List<SettingsDTO>> fetchAllResponse();

    Response<SettingsDTO> fetchByIdResponse(UUID settingsId);

    Response<SettingsDTO> updateResponse(SettingsEntity settings, UUID settingsId);
    

    Response<Void> deleteByIdResponse(UUID settingsId);

}
