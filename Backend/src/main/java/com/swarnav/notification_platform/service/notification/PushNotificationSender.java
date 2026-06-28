package com.swarnav.notification_platform.service.notification;

import org.springframework.stereotype.Service;

import com.swarnav.notification_platform.entity.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushNotificationSender implements NotificationSender {

    @Override
    public void send(Notification notification) {

        log.info("------------------------------------");
        log.info("PUSH NOTIFICATION");
        log.info("Recipient : {}", notification.getRecipient());
        log.info("Message   : {}", notification.getMessage());
        log.info("------------------------------------");

    }
}
