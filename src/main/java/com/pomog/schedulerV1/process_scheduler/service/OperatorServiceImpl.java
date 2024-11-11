package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.repository.OperatorRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SettingsRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SkillRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl extends BaseService<OperatorDTO> implements OperatorService {
    private final OperatorRepository operatorRepository;
    private final SettingsRepository settingsRepository;
    private final SkillRepository skillRepository;
    
    public OperatorServiceImpl(OperatorRepository operatorRepository,
                               SettingsRepository settingsRepository,
                               SkillRepository skillRepository) {
        this.operatorRepository = operatorRepository;
        this.settingsRepository = settingsRepository;
        this.skillRepository = skillRepository;
    }
    
    
    @Override
    public Response<OperatorDTO> saveOperator(OperatorEntity operatorEntity) {
        OperatorEntity savedOperator = operatorRepository.save(operatorEntity);
        return generateResponse(201,"Created", new OperatorDTO(savedOperator));
    }
    
    @Override
    public Response<OperatorDTO> findOperatorByName(String name) {
        return null;
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