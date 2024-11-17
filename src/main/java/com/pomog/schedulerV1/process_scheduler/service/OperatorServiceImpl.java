package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTOFactory;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.repository.OperatorRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SettingsRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SkillRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import jakarta.persistence.CascadeType;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class OperatorServiceImpl extends BaseService<OperatorDTO> implements OperatorService {
    private final OperatorRepository operatorRepository;
    private final SettingsRepository settingsRepository;
    private final SkillRepository skillRepository;
    private final ExceptionFactory exceptionFactory;
    private final OperatorDTOFactory operatorDTOFactory;
    private final ResponseFactory responseFactory;
    private final MessageSource messageSource;
    
    public OperatorServiceImpl(OperatorRepository operatorRepository, SettingsRepository settingsRepository, SkillRepository skillRepository, ExceptionFactory exceptionFactory, OperatorDTOFactory operatorDTOFactory, ResponseFactory responseFactory, MessageSource messageSource) {
        this.operatorRepository = operatorRepository;
        this.settingsRepository = settingsRepository;
        this.skillRepository = skillRepository;
        this.exceptionFactory = exceptionFactory;
        this.operatorDTOFactory = operatorDTOFactory;
        this.responseFactory = responseFactory;
        this.messageSource = messageSource;
    }
    
    @Override
    public Response<OperatorDTO> getResponseSaveOperator(OperatorEntity operatorEntity) {
        OperatorDTO operatorDTO = operatorDTOFactory.createFromEntity(saveOperator(operatorEntity));
        return responseFactory.buildCreatedResponse(getSuccessMessage("operator.save.success"), operatorDTO);
    }
    
    protected OperatorEntity saveOperator(OperatorEntity operatorEntity) {
        return operatorRepository.save(operatorEntity);
    }
    
    @Override
    public Response<OperatorDTO> getResponseFetchOperatorByName(String name) {
        OperatorDTO operatorDTO = operatorDTOFactory.createFromEntity(fetchOperatorEntityByName(name));
        return responseFactory.buildSuccessResponse(getSuccessMessage("operator.fetch.success"), operatorDTO);
    }
    
    protected OperatorEntity fetchOperatorEntityByName(String name) {
        return operatorRepository.findOperatorEntityByNameIgnoreCase(name)
                .orElseThrow(() -> exceptionFactory.createNotFoundException("Operator", "name: " + name));
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
    public Response<OperatorDTO> getResponseUpdateOperator(UUID id, OperatorEntity operatorEntity) {
        OperatorEntity _operatorEntity = operatorRepository.findById(id)
                .orElseThrow(() -> exceptionFactory.createNotFoundException("Operator", "ID: " + id.toString()));
        
        if (operatorEntity.getSettingsEntity() != null) {
            SettingsEntity managedSettings = settingsRepository.findById(operatorEntity.getSettingsEntity().getID())
                    .orElseThrow(() -> exceptionFactory.createNotFoundException("Settings", "ID: " + operatorEntity.getSettingsEntity().getID().toString()));
            managedSettings = operatorEntity.getSettingsEntity();
            _operatorEntity.setSettingsEntity(managedSettings);
        }
        
        if (!operatorEntity.getSkillEntities().isEmpty()) {
            List<SkillEntity> managedSkills = operatorEntity.getSkillEntities().stream()
                    .map(skill -> skillRepository.findById(skill.getID())
                            .orElseThrow(() -> exceptionFactory.createNotFoundException("Skill", "ID: " + skill.getID().toString())))
                    .toList();
            _operatorEntity.setSkillEntities(managedSkills);
        }
        
        _operatorEntity.setName(operatorEntity.getName());
        _operatorEntity.setPrefersNight(operatorEntity.isPrefersNight());
        _operatorEntity.setPrefersWeekend(operatorEntity.isPrefersWeekend());
        
        // Save the updated entity
        OperatorEntity updatedOperator = operatorRepository.save(_operatorEntity);
        return responseFactory.buildSuccessResponse(
                getSuccessMessage("operator.save.success"),
                new OperatorDTO(updatedOperator)
        );
    }
    
    @Override
    public Response<List<OperatorDTO>> getResponseForAllOperators() {
        return createResponseForList(operatorRepository.findAll().stream()
                .map(operatorDTOFactory::createFromEntity)
                .toList());
    }
    
    @Override
    public Response<Void> getResponseForDeleteOperatorByID(UUID id) {
        operatorRepository.deleteById(id);
        return responseFactory.createDeleteSingleResponse(getSuccessMessage("operators.delete.success"));
    }
    
    @Override
    public Response<Void> getResponseForDeleteOperatorByName(String name) {
        operatorRepository.deleteOperatorEntityByName(name);
        return responseFactory.createDeleteSingleResponse(getSuccessMessage("operators.delete.success"));
    }
    
    protected Response<List<OperatorDTO>> createResponseForList(List<OperatorDTO> operatorDTOs) {
        return operatorDTOs.isEmpty()
                ? responseFactory.buildSuccessResponse(getSuccessMessage("operators.fetch.empty"), operatorDTOs)
                : responseFactory.buildSuccessResponse(getSuccessMessage("operators.fetch.success"), operatorDTOs);
    }
    
    protected List<OperatorDTO> getOperatorDTOsByShiftPreference(boolean prefersShift, ShiftType shiftType) {
        return fetchOperatorEntitiesByShiftPreference(prefersShift, shiftType).stream()
                .map(operatorDTOFactory::createFromEntity)
                .toList();
    }
    
    protected List<OperatorEntity> fetchOperatorEntitiesByShiftPreference(boolean prefersShift, ShiftType shiftType) {
        return switch (shiftType) {
            case NIGHT -> operatorRepository.findOperatorEntitiesByPrefersNight(prefersShift);
            case WEEKEND -> operatorRepository.findOperatorEntitiesByPrefersWeekend(prefersShift);
            default -> throw exceptionFactory.createUnsupportedShiftTypeException(shiftType.toString());
        };
    }
    
    @Override
    public Response<List<SkillDTO>> findOperatorEntitiesBySkillEntities_ProcessName(String processName) {
        return null;
    }
    
    protected String getSuccessMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }
}