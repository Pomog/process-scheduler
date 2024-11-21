package com.pomog.schedulerV1.process_scheduler.dto;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DTOFactoryRegistry {
    private final Map<Class<?>, DTOFactory<?, ?>> factories = new HashMap<>();
    
    public <E, D> void registerFactory(Class<E> entityClass, DTOFactory<E, D> factory) {
        factories.put(entityClass, factory);
    }
    
    @SuppressWarnings("unchecked")
    public <E, D> DTOFactory<E, D> getFactory(Class<E> entityClass) {
        return (DTOFactory<E, D>) factories.get(entityClass);
    }
}
