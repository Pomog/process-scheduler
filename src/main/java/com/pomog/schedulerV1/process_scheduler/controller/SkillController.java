package com.pomog.schedulerV1.process_scheduler.controller;


import com.pomog.schedulerV1.process_scheduler.dto.SkillDTO;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.service.SkillsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {
    private final SkillsService skillsService;
    
    public SkillController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }
    
    @GetMapping("/{operatorName}")
    public Response<List<SkillDTO>> getSkillsByOperatorName (@PathVariable String operatorName) {
        return skillsService.findAllByOperatorEntities_Name(operatorName);
    }
    
}
