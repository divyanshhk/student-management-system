package com.divyansh.studentmanagementsystem.project.error;

import com.divyansh.studentmanagementsystem.project.error.exception.DuplicateEnrollmentException;
import com.divyansh.studentmanagementsystem.project.error.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
