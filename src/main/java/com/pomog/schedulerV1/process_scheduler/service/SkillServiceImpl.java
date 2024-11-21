package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.dto.SkillDTOFactory;
import com.pomog.schedulerV1.process_scheduler.entity.SkillEntity;
import com.pomog.schedulerV1.process_scheduler.repository.SkillRepository;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl extends BaseService<SkillEntity, SkillDTO> implements SkillsService{
    private final SkillRepository skillRepository;
    
    public SkillServiceImpl(
            SkillRepository skillRepository,
            ResponseFactory responseFactory,
            MessageSource messageSource,
            SkillDTOFactory dtoFactory
    ) {
        super(responseFactory, messageSource, dtoFactory);
        this.skillRepository = skillRepository;
    }
    
    @Override
    public Response<List<SkillDTO>> findSkillsByLevel(int level) {
        return null;
    }
    
    @Override
    public Response<SkillDTO> findByProcessNameAndStepName(String processName, String stepName) {
        return null;
    }
    
    @Override
    public Response<List<SkillDTO>> findAllByOperatorEntities_Name(String operatorName) {
        List<SkillDTO> skillDTOS = skillRepository.findAllByOperatorEntities_Name(operatorName).stream()
                        .map(this::convertToDTO)
                        .toList();
        return createResponseForList(skillDTOS);
    }
}
