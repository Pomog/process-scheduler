package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTOFactory;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.repository.OperatorRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SettingsRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OperatorServiceImpl extends BaseService<OperatorEntity, OperatorDTO> implements OperatorService {
    private final OperatorRepository operatorRepository;
    private final SettingsRepository settingsRepository;
    private final ExceptionFactory exceptionFactory;
    
    public OperatorServiceImpl(
            OperatorRepository operatorRepository,
            SettingsRepository settingsRepository,
            ExceptionFactory exceptionFactory,
            OperatorDTOFactory operatorDTOFactory,
            ResponseFactory responseFactory,
            MessageSource messageSource
    ) {
        super(responseFactory, messageSource, operatorDTOFactory, exceptionFactory);
        this.operatorRepository = operatorRepository;
        this.settingsRepository = settingsRepository;
        this.exceptionFactory = exceptionFactory;
    }
    
    @Override
    public Response<OperatorDTO> getResponseSaveOperator(OperatorEntity operatorEntity) {
        OperatorDTO operatorDTO = this.convertToDTO(saveOperator(operatorEntity));
        return buildSuccessResponseToSave(operatorDTO);
    }
    
    protected OperatorEntity saveOperator(OperatorEntity operatorEntity) {
        return operatorRepository.save(operatorEntity);
    }
    
    @Override
    public Response<OperatorDTO> getResponseFetchOperatorByName(String name) {
        OperatorDTO operatorDTO = this.convertToDTO(fetchOperatorEntityByName(name));
        return buildSuccessResponseToGet(operatorDTO);
    }
    
    protected OperatorEntity fetchOperatorEntityByName(String name) {
        return operatorRepository.findOperatorEntityByNameIgnoreCase(name)
                .orElseThrow(() -> exceptionFactory.notFoundException("Operator", "name: " + name));
    }
    
    @Override
    public Response<List<OperatorDTO>> getResponseForOperatorsPreferringNightShift(boolean prefersNightShift) {
        return createResponseForList(getOperatorDTOsByShiftPreference(prefersNightShift, ShiftType.NIGHT));
    }
    
    @Override
    public Response<List<OperatorDTO>> getResponseForOperatorsPreferringWeekendShift(boolean prefersWeekendShift) {
        return createResponseForList(getOperatorDTOsByShiftPreference(prefersWeekendShift, ShiftType.WEEKEND));
    }
    
    @Override
    @Transactional
    public Response<OperatorDTO> getResponseUpdateOperator(UUID id, OperatorEntity operatorEntity) {
        OperatorEntity existingOperator = operatorRepository.findById(id)
                .orElseThrow(() -> exceptionFactory.notFoundException("Operator", "ID: " + id.toString()));
        
        updateOperatorFields(existingOperator, operatorEntity);
        
        return buildSuccessResponseToSave(new OperatorDTO(operatorRepository.save(existingOperator)));
    }
    
    private void updateOperatorFields(OperatorEntity existing, OperatorEntity provided) {
        existing.setName(provided.getName());
        existing.setPrefersNight(provided.isPrefersNight());
        existing.setPrefersWeekend(provided.isPrefersWeekend());
        
        if (provided.getSettingsEntity() != null) {
            SettingsEntity newSettings = settingsRepository.save(provided.getSettingsEntity());
            existing.setSettingsEntity(newSettings);
        }
        
        // TODO update Skills
        
        
    }
    
    @Override
    public Response<List<OperatorDTO>> getResponseForAllOperators() {
        return createResponseForList(convertEntitiesToDTOs(operatorRepository.findAll()));
    }
    
    @Override
    public Response<Void> getResponseForDeleteOperatorByID(UUID id) {
        operatorRepository.deleteById(id);
        return buildSuccessResponseToDelete();
    }
    
    @Override
    public Response<Void> getResponseForDeleteOperatorByName(String name) {
        operatorRepository.deleteOperatorEntityByName(name);
        return buildSuccessResponseToDelete();
    }
    
    @Override
    public Response<List<OperatorDTO>> findOperatorEntitiesBySkillEntities_ProcessName(String processName) {
        return createResponseForList(convertEntitiesToDTOs(
                operatorRepository.findOperatorEntitiesBySkillEntities_ProcessName(processName))
        );
    }
    
    protected List<OperatorDTO> getOperatorDTOsByShiftPreference(boolean prefersShift, ShiftType shiftType) {
        return convertEntitiesToDTOs(fetchOperatorEntitiesByShiftPreference(prefersShift, shiftType));
    }
    
    protected List<OperatorEntity> fetchOperatorEntitiesByShiftPreference(boolean prefersShift, ShiftType shiftType) {
        return switch (shiftType) {
            case NIGHT -> operatorRepository.findOperatorEntitiesByPrefersNight(prefersShift);
            case WEEKEND -> operatorRepository.findOperatorEntitiesByPrefersWeekend(prefersShift);
            default -> throw exceptionFactory.createUnsupportedShiftTypeException(shiftType.toString());
        };
    }

}