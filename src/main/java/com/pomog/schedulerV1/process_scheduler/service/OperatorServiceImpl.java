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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl extends BaseService<OperatorDTO> implements OperatorService {
    private final OperatorRepository operatorRepository;
    private final SettingsRepository settingsRepository;
    private final SkillRepository skillRepository;
    private final ExceptionFactory exceptionFactory;
    
    private final OperatorDTOFactory operatorDTOFactory;
    
    public OperatorServiceImpl(OperatorRepository operatorRepository,
                               SettingsRepository settingsRepository,
                               SkillRepository skillRepository,
                               ExceptionFactory exceptionFactory, OperatorDTOFactory operatorDTOFactory) {
        this.operatorRepository = operatorRepository;
        this.settingsRepository = settingsRepository;
        this.skillRepository = skillRepository;
        this.exceptionFactory = exceptionFactory;
        this.operatorDTOFactory = operatorDTOFactory;
    }
    
    
    @Override
    public Response<OperatorDTO> getResponseSaveOperator(OperatorEntity operatorEntity) {
        OperatorEntity savedOperator = operatorRepository.save(operatorEntity);

        return Response.<OperatorDTO>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Operator saved successfully")
                .data(operatorDTOFactory.createFromEntity(savedOperator))
                .build();
    }
    
    @Override
    public Response<OperatorDTO> getResponseFetchOperatorByName(String name) {
        OperatorEntity foundOperator = fetchOperatorEntity(name);
        
        return Response.<OperatorDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Operator found successfully")
                .data(operatorDTOFactory.createFromEntity(foundOperator))
                .build();
    }
    
    protected OperatorEntity fetchOperatorEntity(String name) {
        return operatorRepository.findOperatorEntityByName(name)
                .orElseThrow(() -> exceptionFactory.createNotFoundException("Operator", "name: " + name));
    }
    
    @Override
    public Response<List<OperatorDTO>> findOperatorEntitiesByPrefersNight(boolean preferNights) {
        return null;
    }
    
    @Override
    public Response<List<OperatorDTO>> findOperatorEntitiesByPrefersWeekend(boolean preferWeekends) {
        return null;
    }
    
    @Override
    public Response<List<OperatorDTO>> findOperatorEntitiesBySkillEntities_ProcessNameAndSkillEntities_StepName(String processName, String stepName) {
        return null;
    }
    
    @Override
    public Response<List<SkillDTO>> findOperatorEntitiesBySkillEntities_ProcessName(String processName) {
        return null;
    }
}