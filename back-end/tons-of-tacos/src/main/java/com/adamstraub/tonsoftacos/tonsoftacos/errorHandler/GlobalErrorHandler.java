package com.adamstraub.tonsoftacos.tonsoftacos.errorHandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class GlobalErrorHandler{

    private String message;

    private enum LogStatus{
        STACK_TRACE, MESSAGE_ONLY
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map <String, Object> handleNumberFormatException(
            NumberFormatException e, WebRequest webRequest){
        return createExceptionMessage(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, webRequest);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Map <String, Object> handleEntityNotFoundException(
            EntityNotFoundException e, WebRequest webRequest) {
        return createExceptionMessage(e.getLocalizedMessage(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map <String, Object> handleIllegalArgumentException(
            IllegalArgumentException e, WebRequest webRequest){
        return createExceptionMessage(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Map<String, Object> handleUsernameNotFoundException(
            UsernameNotFoundException e, WebRequest webRequest){
        return  createExceptionMessage(e.getLocalizedMessage(), HttpStatus.FORBIDDEN, webRequest);
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Map<String, Object> handleSignatureException(
            SignatureException e, WebRequest webRequest){
        return  createExceptionMessage(e.getLocalizedMessage(), HttpStatus.FORBIDDEN, webRequest);
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Map<String, Object> handleJwtException(
            JwtException e, WebRequest webRequest){
        return  createExceptionMessage(e.getLocalizedMessage(), HttpStatus.FORBIDDEN, webRequest);
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Map<String, Object> handleMalformedJwtException(
            MalformedJwtException e, WebRequest webRequest){
        return  createExceptionMessage(e.getLocalizedMessage(), HttpStatus.FORBIDDEN, webRequest);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public Map<String, Object> handleExpiredJwtException(
            ExpiredJwtException e, WebRequest webRequest){
        return  createExceptionMessage(e.getMessage(), HttpStatus.FORBIDDEN, webRequest);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Map<String, Object> handleBadCredentialsException(
            BadCredentialsException e, WebRequest webRequest
    ){
        return createExceptionMessage(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED, webRequest);
    }





//    ---------------- Being reworked


// alter to not just create the message but also log the error
// create method to log the error to an internal file
    private Map<String,Object> createExceptionMessage(String e, HttpStatus status, WebRequest webRequest) {

    Map <String, Object> error = new HashMap<>();
    String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

    if(webRequest instanceof ServletWebRequest){
        error.put("uri",
                ((ServletWebRequest)webRequest).getRequest().getRequestURI());
    }
    error.put("message", e);
    error.put("status code", status.value());
    error.put("timestamp", timestamp);
    error.put("reason", status.getReasonPhrase());
    return error;
    }
}
