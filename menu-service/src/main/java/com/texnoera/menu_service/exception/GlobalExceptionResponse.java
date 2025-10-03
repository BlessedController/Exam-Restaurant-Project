package com.texnoera.menu_service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record GlobalExceptionResponse(
        String timestamp,
        String path,
        int statusCode,
        String reasonPhrase,
        String message,
        Map<String, List<String>> validationErrors
) {
}
