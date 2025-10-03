package com.texnoera.notification_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.mail")
@Configuration
@Getter
@Setter
public class MailPropertiesConfig {
    String host;
    int port;
    String username;
    String password;
    String from;

}
