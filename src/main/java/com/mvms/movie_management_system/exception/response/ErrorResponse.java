package com.mvms.movie_management_system.exception.response;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime createdDtm;

    private ErrorType errorType;

    private int errorCode;

    private String message;

    public ErrorResponse() {

    }
    public ErrorResponse(LocalDateTime createdDtm, ErrorType errorType, int errorCode, String message) {
        this.createdDtm = createdDtm;
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.message = message;
    }

    public LocalDateTime getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(LocalDateTime createdDtm) {
        this.createdDtm = createdDtm;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
