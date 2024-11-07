package com.pomog.schedulerV1.process_scheduler.service;


import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ResourceNotFoundException;
import com.pomog.schedulerV1.process_scheduler.repository.SettingsRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SettingsServiceImpl extends BaseService<SettingsDTO> implements SettingsService {
    
    private final SettingsRepository settingsRepository;
    
    public SettingsServiceImpl(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }
    
    @Override
    public Response<SettingsDTO> saveSettings(SettingsEntity settings) {
        SettingsEntity savedSettings = settingsRepository.save(settings);
        return generateResponse(201, "Created", new SettingsDTO(savedSettings));
    }
    
    @Override
    public Response<List<SettingsDTO>> fetchSettingsList() {
        List<SettingsEntity> settingsEntities = (List<SettingsEntity>) settingsRepository.findAll();
        List<SettingsDTO> responseData = settingsEntities.stream().
                map(SettingsDTO::new).toList();
        return new Response<>(200, "OK", responseData);
    }
    
    @Override
    public Response<SettingsDTO> findById(UUID settingsId) {
        SettingsEntity foundSettings = settingsRepository.findById(settingsId).
                orElseThrow(() -> new ResourceNotFoundException("settings with " + settingsId + " not found"));
        return generateResponse(200, "OK", new SettingsDTO(foundSettings));
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
    
    @Override
    public SettingsEntity convertDTOtoEntity(SettingsDTO settingsDTO) {
        SettingsEntity settingsEntity = new SettingsEntity();
        settingsEntity.setName(settingsDTO.getName());
        settingsEntity.setNormalHours(settingsDTO.getNormalHours());
        settingsEntity.setNightCoefficient(settingsDTO.getNightCoefficient());
        settingsEntity.setWeekendCoefficient(settingsDTO.getWeekendCoefficient());
        
        return settingsEntity;
    }
}
