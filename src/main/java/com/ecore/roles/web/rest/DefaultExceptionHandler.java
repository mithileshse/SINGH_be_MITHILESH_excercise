package com.ecore.roles.web.rest;

import com.ecore.roles.exception.ErrorResponse;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.exception.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * DefaultExceptionHandler is a controller advice that handles exceptions thrown in the application.
 */
@Log4j2
@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 
     * Handles ResourceNotFoundException and returns an error response with HTTP status 404.
     * 
     * @param exception the exception to handle.
     * @return a ResponseEntity containing the error response.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ResourceNotFoundException exception) {
        log.error("Resource not found: {}", exception.getMessage());
        return createResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    /**
     * 
     * Handles ResourceExistsException and returns an error response with HTTP status 400.
     * 
     * @param exception the exception to handle.
     * @return a ResponseEntity containing the error response.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ResourceExistsException exception) {
        log.error("Resource already exists: {}", exception.getMessage());
        return createResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * 
     * Handles IllegalStateException and returns an error response with HTTP status 409.
     * 
     * @param exception the exception to handle.
     * @return a ResponseEntity containing the error response.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(IllegalStateException exception) {
        log.error("Illegal state: {}", exception.getMessage());
        return createResponse(HttpStatus.CONFLICT, exception.getMessage());
    }

    /**
     * 
     * Creates a ResponseEntity containing an error response with the given HTTP status and message.
     * 
     * @param status the HTTP status code.
     * @param exception the exception message.
     * @return a ResponseEntity containing the error response.
     */
    private ResponseEntity<ErrorResponse> createResponse(HttpStatus status, String exception) {
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.builder()
                        .status(status.value())
                        .error(exception).build());
    }
}
