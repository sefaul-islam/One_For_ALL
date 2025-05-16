package com.example.One_For_All.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // or another relevant status
public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String message) {
        super(ErrorMessages.INVALID_OPERATION.getMessage());
    }
}
