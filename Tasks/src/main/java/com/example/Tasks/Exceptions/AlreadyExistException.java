package com.example.Tasks.Exceptions;

public class AlreadyExistException extends Exception{
    public AlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
