//package com.adamstraub.tonsoftacos.tonsoftacos.controllers.errorController;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.nio.file.Path;
//
//@RestController
//public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
//    private static final String PATH = "/error";
//    @RequestMapping(PATH)
//    public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request, HttpServletResponse response) throws Throwable{
//            throw (Throwable) request.getAttribute("javax.servlet.error.exception");
//        }
//    }
