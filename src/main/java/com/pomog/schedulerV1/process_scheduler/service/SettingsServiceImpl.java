package com.pomog.schedulerV1.process_scheduler.service;


import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTOFactory;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
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
    private final ExceptionFactory exceptionFactory;
    
    public SettingsServiceImpl(
            SettingsRepository settingsRepository,
            ResponseFactory responseFactory,
            MessageSource messageSource,
            SettingsDTOFactory dtoFactory,
            ExceptionFactory exceptionFactory
    ) {
        super(responseFactory, messageSource, dtoFactory);
        this.settingsRepository = settingsRepository;
        this.dtoFactory = dtoFactory;
        this.exceptionFactory = exceptionFactory;
    }
    
    @Override
    public Response<SettingsDTO> getResponseSave(SettingsEntity settings) {
        SettingsDTO savedSettingsDTO = dtoFactory.createFromEntity(settingsRepository.save(settings));
        return buildSuccessResponseToSave(savedSettingsDTO);
    }
    
    @Override
    public Response<List<SettingsDTO>> getResponseFetchAll() {
        List<SettingsDTO> foundData = StreamSupport.stream(settingsRepository.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .toList();
        
        return createResponseForList(foundData);
    }
    
    @Override
    public Response<SettingsDTO> getResponseFindById(UUID settingsId) {
        SettingsEntity foundSettings = fetchSettingByID(settingsId);
        return buildSuccessResponseToGet(convertToDTO(foundSettings));
    }
    
    private SettingsEntity fetchSettingByID(UUID settingsId) {
        return settingsRepository.findById(settingsId)
                .orElseThrow(() -> exceptionFactory.notFoundException("Settings", settingsId.toString()));
    }
    
    @Override
    public Response<SettingsDTO> getResponseUpdate(SettingsEntity settings, UUID settingsId) {
        SettingsEntity settingsDB = fetchSettingByID(settingsId);
        
        updateSettingsFields(settingsDB, settings);
        
        return buildSuccessResponseToGet(convertToDTO(settingsDB));
    }
    
    private void updateSettingsFields(SettingsEntity settingsDB, SettingsEntity settings) {
        // settings will always have valid and intentional values after the validation layer
        settingsDB.setName(settings.getName());
        settingsDB.setNormalHours(settings.getNormalHours());
        settingsDB.setNightCoefficient(settings.getNightCoefficient());
        settingsDB.setWeekendCoefficient(settings.getWeekendCoefficient());
    }
    
    @Override
    public void getResponseDeleteById(UUID settingsId) {
        settingsRepository.deleteById(settingsId);
    }
    
}
