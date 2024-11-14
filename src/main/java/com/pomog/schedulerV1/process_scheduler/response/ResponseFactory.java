package com.pomog.schedulerV1.process_scheduler.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {
    public <T> Response<T> buildSuccessResponse(String message, T data) {
        return Response.<T>builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(data)
                .build();
    }
    
    public <T> Response<T> buildCreatedResponse(String message, T data) {
        return Response.<T>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(message)
                .data(data)
                .build();
    }
    
    public <T> Response<T> createErrorResponse(String message, HttpStatus status) {
        return Response.<T>builder()
                .statusCode(status.value())
                .message(message)
                .build();
    }

}
