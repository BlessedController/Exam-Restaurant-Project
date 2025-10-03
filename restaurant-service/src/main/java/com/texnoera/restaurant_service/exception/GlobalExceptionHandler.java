package com.texnoera.restaurant_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
    public ResponseEntity<GlobalExceptionResponse> handle(NotFoundException exception, HttpServletRequest request) {
        var body = createBody(request.getRequestURI(), NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), exception.getMessage());

        return ResponseEntity.status(NOT_FOUND).body(body);
    }





}
