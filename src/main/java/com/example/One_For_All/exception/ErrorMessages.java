package com.example.One_For_All.exception;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    USER_NOT_FOUND("User Not Found");
    private final String message;

    ErrorMessages(String message){
        this.message=message;
    }
}
