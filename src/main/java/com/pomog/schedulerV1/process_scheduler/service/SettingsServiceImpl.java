package com.pomog.schedulerV1.process_scheduler.service;


import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.dto.StepDTO;
import com.pomog.schedulerV1.process_scheduler.entity.EquipmentEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.repository.SettingsRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SettingsServiceImpl extends BaseService<SettingsDTO> implements SettingsService {
    @Autowired
    private SettingsRepository settingsRepository;
    @Override
    public Response<SettingsDTO> saveSettings(SettingsEntity settings) {
        SettingsEntity savedSettings = settingsRepository.save(settings);
        return generateResponse(201, "Created", new SettingsDTO(savedSettings));
    }
    
    @Override
    public List<SettingsEntity> fetchDepartmentList() {
        return (List<SettingsEntity>) settingsRepository.findAll();
    }
    
    @Override
    public SettingsEntity updateSettings(SettingsEntity settings, UUID settingsId) {
        SettingsEntity settingsDB = settingsRepository.findById(settingsId).get();
        
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
