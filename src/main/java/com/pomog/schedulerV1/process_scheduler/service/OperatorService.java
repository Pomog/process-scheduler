package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.OperatorDTO;
import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;

import java.util.List;
import java.util.UUID;

public interface OperatorService {

    Response<OperatorDTO> saveOperator(OperatorEntity operatorEntity);
    
    Response<OperatorDTO> findOperatorByName(String name);
    
    Response<List<OperatorDTO>> findOperatorsPreferringNightShifts();

    Response<List<OperatorDTO>> findOperatorsPreferringWeekendShifts();

    Response<List<OperatorDTO>> findOperatorsBySkillId(UUID skillUUID);

    Response<List<SkillDTO>> findSkillsByOperatorName(String operatorName);
}
