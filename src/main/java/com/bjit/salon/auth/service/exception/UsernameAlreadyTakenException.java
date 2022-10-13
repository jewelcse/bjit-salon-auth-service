package com.bjit.salon.auth.service.exception;

public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException(String message){
        super(message);
    }
}
