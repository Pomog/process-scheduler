package response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Response<T> {
    @NonNull
    private Integer status;   // "success" or "error"
    @NonNull
    private String message;   // Message for additional information
    @NonNull
    private T data;           // Generic type for response data
    
    private LocalDateTime timeStamp = LocalDateTime.now();
}

