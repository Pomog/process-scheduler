package com.pomog.schedulerV1.process_scheduler.service;

import com.pomog.schedulerV1.process_scheduler.response.Response;
import com.pomog.schedulerV1.process_scheduler.response.ResponseFactory;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

public abstract class BaseService<T> {
    private final ResponseFactory responseFactory;
    private final MessageSource messageSource;
    
    protected BaseService(ResponseFactory responseFactory, MessageSource messageSource) {
        this.responseFactory = responseFactory;
        this.messageSource = messageSource;
    }
    
    protected Response<T> generateResponse(int status, String message, T data) {
        return new Response<T> (status, message, data);
    }
    
    protected Response<List<T>> createResponseForList(List<T> dtoList) {
        return dtoList.isEmpty()
                ? responseFactory.buildSuccessResponse(getSuccessMessage("fetch.empty"), dtoList)
                : responseFactory.buildSuccessResponse(getSuccessMessage("fetch.success"), dtoList);
    }
    
    protected Response<T> buildSuccessResponseToGet (T data) {
        return responseFactory.buildSuccessResponse(getSuccessMessage("fetch.success"), data);
    }
    
    protected Response<T> buildSuccessResponseToSave (T data) {
        return responseFactory.buildSuccessResponse(getSuccessMessage("save.empty"), data);
    }
    
    protected String getSuccessMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }
}
