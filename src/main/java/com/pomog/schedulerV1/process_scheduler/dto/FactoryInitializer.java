package com.pomog.schedulerV1.process_scheduler.dto;

import com.pomog.schedulerV1.process_scheduler.entity.OperatorEntity;
import com.pomog.schedulerV1.process_scheduler.entity.SettingsEntity;
import org.springframework.stereotype.Component;

@Component
public class FactoryInitializer {
    public FactoryInitializer(
            DTOFactoryRegistry registry,
            SettingsDTOFactory settingsFactory,
            OperatorDTOFactory operatorFactory
    ) {
        registry.registerFactory(SettingsEntity.class, settingsFactory);
        registry.registerFactory(OperatorEntity.class, operatorFactory);
    }
}
