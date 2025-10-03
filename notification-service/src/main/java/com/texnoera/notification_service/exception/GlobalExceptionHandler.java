package com.texnoera.notification_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

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

    @ExceptionHandler(MailCouldNotSendException.class)
    public ResponseEntity<GlobalExceptionResponse> handle(MailCouldNotSendException exception, HttpServletRequest request) {
        var body = createBody(
                request.getRequestURI(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exception.getMessage());

        return ResponseEntity.status(BAD_REQUEST).body(body);
    }


}
