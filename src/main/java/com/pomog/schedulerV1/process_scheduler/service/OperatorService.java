package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OperatorService {

    Response<OperatorDTO> saveOperator(OperatorEntity operatorEntity);
    
    Response<OperatorDTO> findOperatorByName(String name);
    
    Response<List<OperatorDTO>> findOperatorEntitiesByPrefersNight(boolean preferNights);

    Response<List<OperatorDTO>> findOperatorEntitiesByPrefersWeekend(boolean preferWeekends);;

    Response<List<OperatorDTO>> findOperatorEntitiesBySkillEntities_ProcessNameAndSkillEntities_StepName(String processName, String stepName);

    Response<List<SkillDTO>> findOperatorEntitiesBySkillEntities_ProcessName(String processName);

}
