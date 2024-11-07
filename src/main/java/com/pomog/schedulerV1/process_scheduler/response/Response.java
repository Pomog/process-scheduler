package com.pomog.schedulerV1.process_scheduler.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Response<T> {
    @NonNull
    private Integer status;   // "success" or "error"
    @NonNull
    private String message;   // Message for additional information
    private T data;           // Generic type for response data
    
    public Response(@NonNull Integer status, @NonNull String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    private LocalDateTime timeStamp = LocalDateTime.now();
}

