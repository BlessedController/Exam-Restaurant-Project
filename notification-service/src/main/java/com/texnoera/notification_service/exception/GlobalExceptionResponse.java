package com.texnoera.notification_service.exception;

import lombok.Builder;

@Builder
public record GlobalExceptionResponse(
        String timestamp,
        String path,
        int statusCode,
        String reasonPhrase,
        String message
) {
}
