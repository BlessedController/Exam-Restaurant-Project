package com.texnoera.notification_service.listener;

import com.texnoera.notification_service.service.EmailService;
import com.texnoera.notification_service.model.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class NotificationListener {

    EmailService emailService;

    private static final String TOPIC = "create-restaurant-topic";
    private static final String GROUP_ID = "notification-group";
    private static final String CONTAINER_FACTORY = "kafkaListenerContainerFactory";

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID, containerFactory = CONTAINER_FACTORY)
    public void consume(Restaurant restaurant) {
        String message = String.format("📩 New  Restaurant Created:\n- Id: %d\n- Name: %s\n- Address: %s\n",
                restaurant.id(),
                restaurant.name(),
                restaurant.address()
        );

        log.info("📧 Sending email to customer with restaurant details...");

        emailService.sendEmail("mgzlovcontact@gmail.com", message);
    }

}
