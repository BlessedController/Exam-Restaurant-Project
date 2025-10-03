package com.texnoera.notification_service.service;

import com.texnoera.notification_service.config.MailPropertiesConfig;
import com.texnoera.notification_service.exception.MailCouldNotSendException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;


@Service
@Slf4j
@FieldDefaults(level = PRIVATE)
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    final MailPropertiesConfig mailProperties;
    JavaMailSenderImpl mailSender;

    @PostConstruct
    private void init() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    }

    @Override
    public void sendEmail(String email, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(mailProperties.getFrom());
            message.setTo(email);
            message.setSubject("A new Restaurant has created");
            message.setText(content);

            mailSender.send(message);

        } catch (MailException e) {
            log.error("Failed to send mail to {}", email, e);
            throw new MailCouldNotSendException("Mail could not be sent");
        }


    }


}
