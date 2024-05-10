package com.example.doctor_appointment_be.common;

import com.example.doctor_appointment_be.auth.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // CUSTOM EXCEPTIONS

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), "email");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    // DEFAULT EXCEPTIONS PROVIDED BY SPRING

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errorMap.put(error.getField(), error.getDefaultMessage()));
        // TODO: how to use the global ErrorResponse class here?
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    // TODO: Doubt - Should we add all such commonly occurring exception here in the Global Exception handler?
    // Using the general Exception also works but provides more than just the crisp error message required!
    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidUuid(IllegalArgumentException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    // TODO: Need to handle HttpMessageNotReadableException when a POST request has no Request Body
}
