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
        return settingsService.saveResponse(settingsEntity);
    }
    
    @GetMapping
    public Response<List<SettingsDTO>> getAllSettings() {
        return settingsService.fetchAllResponse();
    }
    
    @GetMapping("/{id}")
    public Response<SettingsDTO> getSettingsById(@Valid @PathVariable UUID id) {
        return settingsService.fetchByIdResponse(id);
    }
    
    @PutMapping("/{id}")
    public Response<SettingsDTO> updateSettings(@Valid @PathVariable UUID id, @Valid @RequestBody SettingsEntity settingsEntity) {
        return settingsService.updateResponse(settingsEntity, id);
    }
    
    @DeleteMapping("/{id}")
    public Response<Void> deleteSettingsById(@Valid @PathVariable UUID id) {
        settingsService.deleteByIdResponse(id);
        return new Response<>(200, "Deleted successfully", null);
    }
}
