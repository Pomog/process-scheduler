package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkillsService {
    Response<List<SkillDTO>>findSkillsByLevel(int level);
    Response<SkillDTO> findByProcessNameAndStepName(String processName, String stepName);
    Response<List<SkillDTO>> findAllByOperatorEntities_Name(String operatorName);
    
}
