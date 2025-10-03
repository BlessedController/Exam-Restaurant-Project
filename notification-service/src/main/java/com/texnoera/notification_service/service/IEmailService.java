package com.texnoera.notification_service.service;

public interface IEmailService {
    void sendEmail(String email, String activationCode);
}
