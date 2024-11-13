package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;

import java.util.List;

public interface OperatorService {
    
    Response<OperatorDTO> getResponseSaveOperator(OperatorEntity operatorEntity);
    
    Response<OperatorDTO> getResponseFetchOperatorByName(String name);
    
    Response<List<OperatorDTO>> getResponseForOperatorsPreferringNightShift (boolean preferNights);
    
    Response<List<OperatorDTO>> getResponseForOperatorsPreferringWeekendShift(boolean preferWeekends);
    
    
    Response<List<SkillDTO>> findOperatorEntitiesBySkillEntities_ProcessName(String processName);
    
}
