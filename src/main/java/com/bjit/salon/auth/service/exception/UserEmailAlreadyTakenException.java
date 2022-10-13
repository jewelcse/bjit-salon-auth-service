package com.bjit.salon.auth.service.exception;

public class UserEmailAlreadyTakenException extends RuntimeException{

    public UserEmailAlreadyTakenException(String message){
        super(message);
    }
}
