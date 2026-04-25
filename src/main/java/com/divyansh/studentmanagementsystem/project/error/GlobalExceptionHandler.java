package com.divyansh.studentmanagementsystem.project.error;

import com.divyansh.studentmanagementsystem.project.error.exception.DuplicateEnrollmentException;
import com.divyansh.studentmanagementsystem.project.error.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
       ApiError apiError = new ApiError("Resource not found ", HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEnrollmentException.class)
    public ResponseEntity<ApiError> handleDuplicateEnrollmentException(DuplicateEnrollmentException ex) {
        ApiError apiError = new ApiError("Already enrolled ", HttpStatus.CONFLICT);
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

//        Map<String, Object> response = new HashMap<>();
//        response.put("status", 400);
//        response.put("errors", errors);
//        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(errors);
    }
}
