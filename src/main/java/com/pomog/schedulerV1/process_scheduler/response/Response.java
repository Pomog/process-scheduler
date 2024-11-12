package com.pomog.schedulerV1.process_scheduler.response;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Standard response wrapper for API responses.
 * @param <T> the type of response data
 */
@Data
@RequiredArgsConstructor
public class Response<T> {
    @NonNull
    private Integer statusCode;   // HTTP response code
    @NonNull
    private String message;   // Message for additional information
    private T data;           // Generic type for response data
    private LocalDateTime timeStamp = LocalDateTime.now(); // Timestamp of response
    
    @Builder
    public Response(@NonNull Integer statusCode, @NonNull String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

}

