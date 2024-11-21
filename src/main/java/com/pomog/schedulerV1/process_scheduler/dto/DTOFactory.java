package com.pomog.schedulerV1.process_scheduler.dto;

public interface DTOFactory <E, D> {
    D createFromEntity(E entity);
}