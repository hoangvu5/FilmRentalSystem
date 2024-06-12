package com.mvms.movie_management_system.exception.custom;

public class RecordFoundException extends RuntimeException {

    public RecordFoundException() {
    }

    public RecordFoundException(String message) { super(message); }
}
