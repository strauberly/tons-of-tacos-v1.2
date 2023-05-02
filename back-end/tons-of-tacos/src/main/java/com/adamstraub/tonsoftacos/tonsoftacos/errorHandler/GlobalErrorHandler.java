package com.adamstraub.tonsoftacos.tonsoftacos.errorHandler;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
@Data
@RestControllerAdvice
public class GlobalErrorHandler {

    private String message;

    private enum LogStatus{
        STACK_TRACE, MESSAGE_ONLY
    }
// updated
//-------------- reworked

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map <String, Object> handleNumberFormatException(
            NumberFormatException e, WebRequest webRequest){
        String body = "Check input format, consult the docs if need be. Try just a number.";
        return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, body);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Map <String, Object> handleEntityNotFoundException(
            EntityNotFoundException e, WebRequest webRequest) {
        String body = "You have chosen something that does not exist. Consult the docs and double check your input.";
        return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, body);
    }


//    ---------------- Being reworked


//
//    @ExceptionHandler(InvalidPropertiesFormatException.class)
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//    public Map <String, Object> handleIllegalArgumentException(
//            InvalidPropertiesFormatException e, WebRequest webRequest){
//        return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
//    }
//    @ExceptionHandler(TypeMismatchException.class)
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//    public Map <String, Object> handleTypeMismatchException(
//            TypeMismatchException e, WebRequest webRequest){
//        return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
//    }
//
//    @ExceptionHandler(NoSuchElementException.class)
//    @ResponseStatus(code = HttpStatus.NOT_FOUND)
//    public Map <String, Object> handleNoSuchElementException(
//            NoSuchElementException e, WebRequest webRequest){
//        return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
//    }
//-------------- rework
// alter this to not just create the message but also log the error
// create method to log the error to an internal file
    private Map<String,Object> createExceptionMessage(Exception e, HttpStatus status, WebRequest webRequest, String body) {

    Map <String, Object> error = new HashMap<>();
    String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

    if(webRequest instanceof ServletWebRequest){
        error.put("uri",
                ((ServletWebRequest)webRequest).getRequest().getRequestURI());
    }
    error.put("message", body);
    error.put("status code", status.value());
    error.put("timestamp", timestamp);
    error.put("reason", status.getReasonPhrase());
    return error;
    }
//private ResponseEntity<Object> createExceptionMessage(Exception e, HttpStatus status, WebRequest webRequest, String body) {
//
//    Map <String, Object> error = new HashMap<>();
//    String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
//
//    if(webRequest instanceof ServletWebRequest){
//        error.put("uri",
//                ((ServletWebRequest)webRequest).getRequest().getRequestURI());
//    }
//    error.put("message", body);
////    error.put("message", e.getMessage());
//    error.put("status code", status.value());
//    error.put("timestamp", timestamp);
//    error.put("reason", status.getReasonPhrase());
//    return error;
//}
}
