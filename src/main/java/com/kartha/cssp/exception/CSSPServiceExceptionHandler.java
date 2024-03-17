package com.kartha.cssp.exception;

import com.kartha.cssp.response.CSSPErrorResponse;
import com.kartha.cssp.response.ErrorMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CSSPServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CSSPServiceException.class)
    public final ResponseEntity<CSSPErrorResponse> handleAllExceptions(CSSPServiceException ex, WebRequest request) {
        CSSPErrorResponse csspErrorResponse = new CSSPErrorResponse();
        csspErrorResponse.setTimestamp(LocalDateTime.now());
        csspErrorResponse.setErrorModel(new ErrorMessages(ex.getErrorCode(), ex.getErrorMessage()));
        csspErrorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(csspErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    //         HttpHeaders headers,
    //         HttpStatus status,
    //         WebRequest request) {

    //     CSSPServiceException error = new CSSPServiceException("Request validation failed.",
    //             HttpStatus.BAD_REQUEST.toString());
    //     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    // }

}
