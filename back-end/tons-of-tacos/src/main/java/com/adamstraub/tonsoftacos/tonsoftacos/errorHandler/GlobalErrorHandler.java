package com.adamstraub.tonsoftacos.tonsoftacos.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// still needs to be implemented
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map <String, Object> handleNumberException(
            NumberFormatException e, WebRequest webRequest){
        return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest);
    }




    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Map <String, Object> handleNoSuchElementException(
            NoSuchElementException e, WebRequest webRequest){
        return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest);
    }

    private Map<String,Object> createExceptionMessage(Exception e, HttpStatus status, WebRequest webRequest) {
    Map <String, Object> error = new HashMap<>();
    String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

    if(webRequest instanceof ServletWebRequest){
        error.put("uri",
                ((ServletWebRequest)webRequest).getRequest().getRequestURI());
    }
    error.put("message", e.toString());
    error.put("status code", HttpStatus.NOT_FOUND.value());
    error.put("tiemstamp", timestamp);
    error.put("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
    return error;
    }
}
