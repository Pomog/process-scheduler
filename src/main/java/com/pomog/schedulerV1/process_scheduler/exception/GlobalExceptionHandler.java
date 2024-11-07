package com.pomog.schedulerV1.process_scheduler.exception;


import com.pomog.schedulerV1.process_scheduler.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    //    Handles validation errors when a method argument fails validation, for @Valid-annotated parameters in controllers.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Response<Map<String, String>> response = new Response<>(HttpStatus.BAD_REQUEST.value(), "Validation error", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    //     Handles validation errors binding request parameters to Java objects fails
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Response<Object>> handleBindException(BindException ex) {
        Response<Object> response = new Response<>(HttpStatus.BAD_REQUEST.value(), "Binding error", ex.getAllErrors());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    //   Catches errors when a method argument type does not match
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response<String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Response<String> response = new Response<>(HttpStatus.BAD_REQUEST.value(), "Wrong ID format", ex.getParameter().toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<String>> handleNotFoundException(ResourceNotFoundException ex) {
        Response<String> response = new Response<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    // Fallback for other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleGlobalException(Exception ex) {
        Response<String> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
