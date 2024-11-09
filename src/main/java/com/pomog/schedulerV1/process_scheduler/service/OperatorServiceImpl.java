package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;
import com.pomog.schedulerV1.process_scheduler.repository.OperatorRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SettingsRepository;
import com.pomog.schedulerV1.process_scheduler.repository.SkillRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Transactional
    public Response<OperatorDTO> saveOperator(OperatorEntity operatorEntity) {
        // Step 1: Ensure operator ID is set (if not already)
        if (operatorEntity.getID() == null) {
            operatorEntity.setID(UUID.randomUUID());
        }
        
        // Step 2: Save or fetch SettingsEntity (based on the name)
        SettingsEntity settingsEntity = settingsRepository.findSettingsByOperatorName(operatorEntity.getSettingsEntity().getName())
                .orElseGet(() -> settingsRepository.save(operatorEntity.getSettingsEntity()));
        operatorEntity.setSettingsEntity(settingsEntity);
        
        // Step 3: Process skillEntities (save or fetch each SkillEntity)
        List<SkillEntity> linkedSkills = new ArrayList<>();
        
        for (SkillEntity skill : operatorEntity.getSkillEntities()) {
            SkillEntity foundSkill = skillRepository.findByProcessNameAndStepName(skill.getProcessName(), skill.getStepName())
                    .orElseGet(() -> skillRepository.save(skill));
            
            // Add the operator to the skill's operator list
            foundSkill.addOperator(operatorEntity);
            
            // Add the skill to the linkedSkills list
            linkedSkills.add(foundSkill);
        }
        
        // Step 4: Set the linked skills in the operator
        operatorEntity.setSkillEntities(linkedSkills);
        
        // Step 5: Save the operator entity (this will persist operator, settings, and skills if new)
        OperatorEntity savedOperator = operatorRepository.save(operatorEntity);
        
        // Return the response with the saved operator DTO
        return generateResponse(201, "Operator saved successfully", new OperatorDTO(savedOperator));
    }
    
    @Override
    public Response<OperatorDTO> findOperatorByName(String name) {
        return null;
    }
    
    @Override
    public Response<List<OperatorDTO>> findOperatorsPreferringNightShifts() {
        return null;
    }
    
    @Override
    public Response<List<OperatorDTO>> findOperatorsPreferringWeekendShifts() {
        return null;
    }
    
    @Override
    public Response<List<OperatorDTO>> findOperatorsBySkillId(UUID skillUUID) {
        return null;
    }
    
    @Override
    public Response<List<SkillDTO>> findSkillsByOperatorName(String operatorName) {
        return null;
    }
}