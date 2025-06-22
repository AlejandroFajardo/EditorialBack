package com.uptc.edu.backendTemplate.exception;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;


//import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;


@ControllerAdvice
public class ExceptionController {
    /* This is already covered by JDBC since DAO is an obsolete artifact
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleException(DataIntegrityViolationException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
     */

    @ExceptionHandler(HttpClientErrorException.class)
    public void handleException(HttpClientErrorException e, HttpServletResponse response) throws IOException {
        response.sendError(e.getStatusCode().value(), e.getStatusText());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleException(ResourceNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }


}
