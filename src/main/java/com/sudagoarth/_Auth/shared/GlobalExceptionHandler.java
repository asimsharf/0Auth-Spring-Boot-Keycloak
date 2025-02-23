package com.sudagoarth._Auth.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.ConnectException;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle all unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        logger.error("Unexpected error occurred", ex);
        return buildErrorResponse("An unexpected error occurred.", "INTERNAL_ERROR", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle 404 errors
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFound(NoHandlerFoundException ex) {
        return buildErrorResponse("Resource not found.", "NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle authentication failures (401)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(AuthenticationException ex) {
        return buildErrorResponse("Authentication failed.", "AUTH_ERROR", ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // Handle access denied (403)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return buildErrorResponse("Access denied.", "ACCESS_DENIED", ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    // Handle validation errors (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
        return buildErrorResponse("Validation error.", "VALIDATION_ERROR", ex.getBindingResult().toString(), HttpStatus.BAD_REQUEST);
    }

    // Handle network errors (503)
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ApiResponse> handleConnectException(ConnectException ex) {
        return buildErrorResponse("Network connection error.", "NETWORK_ERROR", ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    // Handle specific status exceptions
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse> handleResponseStatusException(ResponseStatusException ex) {
        return buildErrorResponse("Error occurred.", ex.getStatusCode().toString(), ex.getReason(), HttpStatus.valueOf(ex.getStatusCode().value()));
    }

    // Helper method to build error responses
    private ResponseEntity<ApiResponse> buildErrorResponse(String message, String code, String details, HttpStatus status) {
        ApiResponse response = new ApiResponse(message, status.value(), null, code, false, details, Instant.now());
        return new ResponseEntity<>(response, status);
    }
}
