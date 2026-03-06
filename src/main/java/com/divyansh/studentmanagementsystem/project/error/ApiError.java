package com.divyansh.studentmanagementsystem.project.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus httpCode;

    public ApiError(){ this.timeStamp = LocalDateTime.now();}
    public ApiError(String error, HttpStatus httpCode) {
        this();
        this.error = error;
        this.httpCode = httpCode;
    }
}
