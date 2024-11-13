package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTOFactory;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.repository.OperatorRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SettingsRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SkillRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;

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
        return responseFactory.createCreatedResponse(getSuccessMessage("operator.save.success"), operatorDTO);
    }
    
    protected OperatorEntity saveOperator(OperatorEntity operatorEntity) {
        return operatorRepository.save(operatorEntity);
    }
    
    @Override
    public Response<OperatorDTO> getResponseFetchOperatorByName(String name) {
        OperatorDTO operatorDTO = operatorDTOFactory.createFromEntity(fetchOperatorEntityByName(name));
        return responseFactory.createSuccessResponse(getSuccessMessage("operator.fetch.success"), operatorDTO);
    }
    
    protected OperatorEntity fetchOperatorEntityByName(String name) {
        return operatorRepository.findOperatorEntityByName(name)
                .orElseThrow(() -> exceptionFactory.createNotFoundException("Operator", "name: " + name));
    }
    @Override
    public Response<List<OperatorDTO>> getResponseWithOperatorsPreferringNightShift(boolean prefersNightShift) {
        return findOperatorsByPreference(prefersNightShift, this::fetchOperatorEntityByPreferringNightShifts);
    }
    
    @Override
    public Response<List<OperatorDTO>> getResponseWithOperatorsPreferringWeekendShift(boolean prefersWeekendShift) {
        return findOperatorsByPreference(prefersWeekendShift, this::fetchOperatorEntityByPreferringWeekendShift);
    }

    protected Response<List<OperatorDTO>> findOperatorsByPreference(boolean prefersShift, Function<Boolean, List<OperatorEntity>> fetchFunction) {
        List<OperatorDTO> operatorDTOs = mapOperatorEntitiesToDTOs(prefersShift, fetchFunction);
        return operatorDTOs.isEmpty()
                ? responseFactory.createSuccessResponse(getSuccessMessage("operators.fetch.empty"), operatorDTOs)
                : responseFactory.createSuccessResponse(getSuccessMessage("operators.fetch.success"), operatorDTOs);
    }
    
    protected List<OperatorDTO> mapOperatorEntitiesToDTOs(boolean prefersShift, Function<Boolean, List<OperatorEntity>> fetchFunction) {
        return fetchFunction.apply(prefersShift).stream()
                .map(OperatorDTO::new)
                .toList();
    }
    
    protected List<OperatorEntity> fetchOperatorEntityByPreferringNightShifts(boolean preferNightShift) {
        return operatorRepository.findOperatorEntitiesByPrefersNight(preferNightShift);
    }
    
    protected List<OperatorEntity> fetchOperatorEntityByPreferringWeekendShift(boolean preferNightShift) {
        return operatorRepository.findOperatorEntitiesByPrefersWeekend(preferNightShift);
    }

    @Override
    public Response<List<OperatorDTO>> findOperatorEntitiesBySkillEntities_ProcessNameAndSkillEntities_StepName(String processName, String stepName) {
        return null;
    }
    
    @Override
    public Response<List<SkillDTO>> findOperatorEntitiesBySkillEntities_ProcessName(String processName) {
        return null;
    }
    
    protected String getSuccessMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }
}