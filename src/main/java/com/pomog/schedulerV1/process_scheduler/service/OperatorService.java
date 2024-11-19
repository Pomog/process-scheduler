package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;

import java.util.List;
import java.util.UUID;

public interface OperatorService {
    
    Response<OperatorDTO> getResponseSaveOperator(OperatorEntity operatorEntity);
    
    Response<OperatorDTO> getResponseFetchOperatorByName(String name);
    
    Response<List<OperatorDTO>> getResponseForOperatorsPreferringNightShift (boolean preferNights);
    
    Response<List<OperatorDTO>> getResponseForOperatorsPreferringWeekendShift(boolean preferWeekends);
    
    Response<OperatorDTO> getResponseUpdateOperator (UUID id, OperatorEntity operatorEntity);
    
    Response<List<OperatorDTO>> getResponseForAllOperators();
    
    Response<Void> getResponseForDeleteOperatorByID(UUID id);
    
    Response<List<OperatorDTO>> findOperatorEntitiesBySkillEntities_ProcessName(String processName);
    
    Response<Void> getResponseForDeleteOperatorByName(String name);
}
