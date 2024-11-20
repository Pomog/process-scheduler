package com.pomog.schedulerV1.process_scheduler.service;


import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTOFactory;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ResourceNotFoundException;
import com.pomog.schedulerV1.process_scheduler.repository.SettingsRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class SettingsServiceImpl extends BaseService<SettingsDTO> implements SettingsService {
    
    private final SettingsRepository settingsRepository;
    private final SettingsDTOFactory settingsDTOFactory;
    
    public SettingsServiceImpl(SettingsRepository settingsRepository, ResponseFactory responseFactory, MessageSource messageSource, SettingsDTOFactory settingsDTOFactory) {
        super(responseFactory, messageSource);
        this.settingsRepository = settingsRepository;
        this.settingsDTOFactory = settingsDTOFactory;
    }
    
    @Override
    public Response<SettingsDTO> saveSettings(SettingsEntity settings) {
        SettingsDTO savedSettingsDTO= settingsDTOFactory.createFromEntity(settingsRepository.save(settings));
        return buildSuccessResponseToSave(savedSettingsDTO);
    }
    
    @Override
    public Response<List<SettingsDTO>> fetchSettingsList() {
        List<SettingsDTO> foundData = StreamSupport.stream(settingsRepository.findAll().spliterator(), false)
                .map(SettingsDTO::new)
                .toList();

        return createResponseForList(foundData);
    }
    
    @Override
    public Response<SettingsDTO> findById(UUID settingsId) {
        SettingsEntity foundSettings = settingsRepository.findById(settingsId).
                orElseThrow(() -> new ResourceNotFoundException("settings with " + settingsId + " not found"));
        return buildSuccessResponseToGet(new SettingsDTO(foundSettings));
    }
    
    @Override
    public SettingsEntity updateSettings(SettingsEntity settings, UUID settingsId) {
        SettingsEntity settingsDB = settingsRepository.findById(settingsId)
                .orElseThrow(() -> new ResourceNotFoundException("Settings with ID " + settingsId + " not found"));
        
        if (!"".equalsIgnoreCase(settings.getName())) {
            settingsDB.setName(settings.getName());
        }
        
        if (0.0 != settings.getNormalHours()) {
            settingsDB.setNormalHours(settings.getNormalHours());
        }
        
        if (0.0 != settings.getNightCoefficient()) {
            settingsDB.setNightCoefficient(settings.getNightCoefficient());
        }
        
        if (0.0 != settings.getWeekendCoefficient()) {
            settingsDB.setWeekendCoefficient(settings.getWeekendCoefficient());
        }
        
        return settingsRepository.save(settingsDB);
    }
    
    @Override
    public void deleteSettingsById(UUID settingsId) {
        settingsRepository.deleteById(settingsId);
    }

}
