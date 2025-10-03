package com.texnoera.notification_service.exception;

public class MailCouldNotSendException extends RuntimeException {
    public MailCouldNotSendException(String message) {
        super(message);
    }
}
