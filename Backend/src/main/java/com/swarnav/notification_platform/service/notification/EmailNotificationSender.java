package com.swarnav.notification_platform.service.notification;

import org.springframework.stereotype.Service;

import com.swarnav.notification_platform.entity.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailNotificationSender implements NotificationSender {

    @Override
    public void send(Notification notification) {

        log.info("------------------------------------");
        log.info("EMAIL NOTIFICATION");
        log.info("Recipient : {}", notification.getRecipient());
        log.info("Message   : {}", notification.getMessage());
        log.info("------------------------------------");

    }
}
