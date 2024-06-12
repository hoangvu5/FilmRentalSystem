package com.mvms.movie_management_system.exception.handler;

import com.mvms.movie_management_system.exception.custom.RecordFoundException;
import com.mvms.movie_management_system.exception.custom.RecordNotFoundException;
import com.mvms.movie_management_system.exception.response.ErrorResponse;
import com.mvms.movie_management_system.exception.response.ErrorType;
import com.mvms.movie_management_system.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @Autowired
    private DateUtils dateUtils;

    @ExceptionHandler({RecordNotFoundException.class, RecordFoundException.class})
    public ResponseEntity<ErrorResponse> handleBusinessException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCreatedDtm(dateUtils.getLocalDateTime());
        errorResponse.setErrorType(ErrorType.SERVICE_EXCEPTION);
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInputFormatException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCreatedDtm(dateUtils.getLocalDateTime());
        errorResponse.setMessage("Incorrect JSON format. ");
        errorResponse.setErrorType(ErrorType.INPUT_FORMAT_EXCEPTION);
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInputInvalidException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCreatedDtm(dateUtils.getLocalDateTime());
        errorResponse.setMessage("Invalid input. ");
        errorResponse.setErrorType(ErrorType.INPUT_FORMAT_EXCEPTION);
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCreatedDtm(dateUtils.getLocalDateTime());
        errorResponse.setMessage("Data access error. ");
        errorResponse.setErrorType(ErrorType.DATA_ACCESS_EXCEPTION);
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
