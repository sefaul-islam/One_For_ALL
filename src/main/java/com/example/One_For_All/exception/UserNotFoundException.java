package com.example.One_For_All.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super(ErrorMessages.USER_NOT_FOUND.getMessage());
    }
}
