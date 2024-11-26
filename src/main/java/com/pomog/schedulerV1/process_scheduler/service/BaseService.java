package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.dto.DTOFactory;
import com.pomog.schedulerV1.process_scheduler.exception.ExceptionFactory;
import com.pomog.schedulerV1.process_scheduler.exception.ResourceNotFoundException;
import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

public abstract class BaseService<E, D> {
    private final ResponseFactory responseFactory;
    private final MessageSource messageSource;
    private final DTOFactory<E, D> dtoFactory;
    private final ExceptionFactory exceptionFactory;
    
    protected BaseService(ResponseFactory responseFactory, MessageSource messageSource, DTOFactory<E, D> dtoFactory, ExceptionFactory exceptionFactory) {
        this.responseFactory = responseFactory;
        this.messageSource = messageSource;
        this.dtoFactory = dtoFactory;
        this.exceptionFactory = exceptionFactory;
    }
    
    protected Response<D> generateResponse(int status, String message, D data) {
        return new Response<D> (status, message, data);
    }
    
    protected Response<List<D>> createResponseForList(List<D> dtoList) {
        return dtoList.isEmpty()
                ? responseFactory.buildSuccessResponse(getSuccessMessage("fetch.empty"), dtoList)
                : responseFactory.buildSuccessResponse(getSuccessMessage("fetch.success"), dtoList);
    }
    
    protected Response<D> buildSuccessResponseToGet (D data) {
        return responseFactory.buildSuccessResponse(getSuccessMessage("fetch.success"), data);
    }
    
    protected Response<D> buildSuccessResponseToSave (D data) {
        return responseFactory.buildSuccessResponse(getSuccessMessage("save.success"), data);
    }
    
    protected Response<Void> buildSuccessResponseToDelete () {
        return responseFactory.createDeleteSingleResponse(getSuccessMessage("delete.success"));
    }
    
    protected ResourceNotFoundException buildNotFoundException(String entityName) {
        return exceptionFactory.notFoundException(entityName);
    }
    
    protected String getSuccessMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }
    
    protected D convertToDTO(E entity) {
        if (entity == null) {
            throw exceptionFactory.notFoundException(getSuccessMessage("fetch.empty"));
        }
        return dtoFactory.createFromEntity(entity);
    }
    
    protected List<D> convertEntitiesToDTOs(List<E> entities) {
        return entities.stream().map(this::convertToDTO).toList();
    }
}
