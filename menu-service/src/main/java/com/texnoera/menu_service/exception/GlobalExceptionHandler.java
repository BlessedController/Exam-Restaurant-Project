package com.texnoera.menu_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.stream.Collectors.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ZONE_ID = "Asia/Baku";

    private String getCurrentTimestamp() {
        return ZonedDateTime.now(ZoneId.of(ZONE_ID))
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }


    private GlobalExceptionResponse createBody(String path,
                                               int statusCode,
                                               String reasonPhrase,
                                               String message) {
        String timestamp = getCurrentTimestamp();

        return GlobalExceptionResponse.builder()
                .timestamp(timestamp)
                .path(path)
                .statusCode(statusCode)
                .reasonPhrase(reasonPhrase)
                .message(message)
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalExceptionResponse> handle(NotFoundException exception,
                                                          HttpServletRequest request) {
        var body = createBody(request.getRequestURI(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                exception.getMessage());

        return status(NOT_FOUND).body(body);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalExceptionResponse> handle(MethodArgumentNotValidException exception,
                                                          HttpServletRequest request) {

        var validationErrors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(groupingBy(FieldError::getField, mapping(FieldError::getDefaultMessage, toList())));

        var body = GlobalExceptionResponse.builder()
                .timestamp(getCurrentTimestamp())
                .path(request.getRequestURI())
                .statusCode(BAD_REQUEST.value())
                .reasonPhrase(BAD_REQUEST.getReasonPhrase())
                .message("Validation errors detected")
                .validationErrors(validationErrors)
                .build();

        return status(BAD_REQUEST).body(body);
    }


}
