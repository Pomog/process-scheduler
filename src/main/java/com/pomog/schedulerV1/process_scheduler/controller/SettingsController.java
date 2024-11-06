package com.pomog.schedulerV1.process_scheduler.controller;

import com.pomog.schedulerV1.process_scheduler.dto.SettingsDTO;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.service.SettingsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/settings")
public class SettingsController {
    
    private final SettingsService settingsService;
    
    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }
    
    @PostMapping
    public Response<SettingsDTO> saveSettings(@Valid @RequestBody SettingsEntity settingsEntity) {
        return settingsService.saveSettings(settingsEntity);
    }
    
    @GetMapping
    public Response<List<SettingsDTO>> getAllSettings() {
        return settingsService.fetchSettingsList();
    }
    
    @GetMapping("/{id}")
    public Response<SettingsDTO> getSettingsById(@PathVariable UUID id) {
        return settingsService.findById(id);
    }
}
