package com.mvms.movie_management_system.exception.handler;

import com.mvms.movie_management_system.exception.custom.RecordNotFoundException;
import com.mvms.movie_management_system.exception.response.ErrorResponse;
import com.mvms.movie_management_system.exception.response.ErrorType;
import com.mvms.movie_management_system.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @Autowired
    private DateUtils dateUtils;

    @ExceptionHandler(RecordNotFoundException.class)
    public ErrorResponse handleBusinessException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCreatedDtm(dateUtils.getLocalDateTime());
        errorResponse.setErrorType(ErrorType.SERVICE_EXCEPTION);
        errorResponse.setErrorCode(HttpStatus.NO_CONTENT.value());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }
}
