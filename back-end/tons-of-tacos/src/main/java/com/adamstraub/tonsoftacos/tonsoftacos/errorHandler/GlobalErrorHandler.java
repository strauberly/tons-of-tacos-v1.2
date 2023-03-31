package com.adamstraub.tonsoftacos.tonsoftacos.errorHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalErrorHandler {

    private enum LogStatus{
        STACK_TRACE, MESSAGE_ONLY
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Map <String, Object> handleEntityNotFoundfException(
            EntityNotFoundException e, WebRequest webRequest) {
        return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
    }

    @ExceptionHandler(InvalidPropertiesFormatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map <String, Object> handleIllegalArgumentException(
            InvalidPropertiesFormatException e, WebRequest webRequest){
        return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
    }
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map <String, Object> handleTypeMismatchException(
            TypeMismatchException e, WebRequest webRequest){
        return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map <String, Object> handleNumberFormatException(
            NumberFormatException e, WebRequest webRequest){
        return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Map <String, Object> handleNoSuchElementException(
            NoSuchElementException e, WebRequest webRequest){
        return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
    }

    private Map<String,Object> createExceptionMessage(Exception e, HttpStatus status, WebRequest webRequest,
                                                      LogStatus logStatus) {
    Map <String, Object> error = new HashMap<>();
    String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

    if(webRequest instanceof ServletWebRequest){
        error.put("uri",
                ((ServletWebRequest)webRequest).getRequest().getRequestURI());
    }
    error.put("message", e.toString());
    error.put("closed code", status.value());
    error.put("timestamp", timestamp);
    error.put("reason", status.getReasonPhrase());
    return error;
    }
}
