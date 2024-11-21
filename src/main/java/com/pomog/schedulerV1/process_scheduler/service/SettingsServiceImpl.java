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
public class SettingsServiceImpl extends BaseService<SettingsEntity, SettingsDTO> implements SettingsService {
    
    private final SettingsRepository settingsRepository;
    private final SettingsDTOFactory dtoFactory;
    
    public SettingsServiceImpl(
            SettingsRepository settingsRepository,
            ResponseFactory responseFactory,
            MessageSource messageSource,
            SettingsDTOFactory dtoFactory) {
        super(responseFactory, messageSource, dtoFactory);
        this.settingsRepository = settingsRepository;
        this.dtoFactory = dtoFactory;
        //        TODO implement universal DTO factory
        
    }
    
    @Override
    public Response<SettingsDTO> saveSettings(SettingsEntity settings) {
        SettingsDTO savedSettingsDTO = dtoFactory.createFromEntity(settingsRepository.save(settings));
        return buildSuccessResponseToSave(savedSettingsDTO);
    }
    
    @Override
    public Response<List<SettingsDTO>> fetchSettingsList() {
        List<SettingsDTO> foundData = StreamSupport.stream(settingsRepository.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .toList();
        
        return createResponseForList(foundData);
    }
    
    @Override
    public Response<SettingsDTO> findById(UUID settingsId) {
        SettingsEntity foundSettings = settingsRepository.findById(settingsId).
                orElseThrow(() -> new ResourceNotFoundException("settings with " + settingsId + " not found"));
        return buildSuccessResponseToGet(convertToDTO(foundSettings));
    }
    
    @Override
    public SettingsEntity updateSettings(SettingsEntity settings, UUID settingsId) {
        SettingsEntity settingsDB = settingsRepository.findById(settingsId)
                .orElseThrow(() -> new ResourceNotFoundException("Settings with ID " + settingsId + " not found"));
        // settings will always have valid and intentional values after the validation layer
        settingsDB.setName(settings.getName());
        settingsDB.setNormalHours(settings.getNormalHours());
        settingsDB.setNightCoefficient(settings.getNightCoefficient());
        settingsDB.setWeekendCoefficient(settings.getWeekendCoefficient());
        
        return settingsDB;
    }
    
    @Override
    public void deleteSettingsById(UUID settingsId) {
        settingsRepository.deleteById(settingsId);
    }
    
}
