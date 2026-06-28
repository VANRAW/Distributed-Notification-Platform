package com.swarnav.notification_platform.service;

import java.time.LocalDateTime;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.swarnav.notification_platform.entity.Notification;
import com.swarnav.notification_platform.enums.NotificationStatus;
import com.swarnav.notification_platform.repository.NotificationRepository;
import com.swarnav.notification_platform.service.notification.NotificationSender;
import com.swarnav.notification_platform.service.notification.NotificationSenderFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final NotificationRepository notificationRepository;
    private final NotificationSenderFactory notificationSenderFactory;

    @Override
    public void onMessage(Message message, byte[] pattern) {

        Notification notification = null;

        try {

            // Convert Redis message to Notification ID
            String id = new String(message.getBody());
            Long notificationId = Long.parseLong(id);

            log.info("Received Notification ID : {}", notificationId);

            // Fetch Notification from Database
            notification = notificationRepository.findById(notificationId)
                    .orElseThrow(() ->
                            new RuntimeException("Notification not found"));

            // Mark as PROCESSING
            notification.setStatus(NotificationStatus.PROCESSING);
            notification.setLastAttemptAt(LocalDateTime.now());

            notificationRepository.save(notification);

            log.info("Processing Notification for {}",
                    notification.getRecipient());

            // Get Appropriate Sender
            NotificationSender sender =
                    notificationSenderFactory.getSender(
                            notification.getNotificationType());

            // Send Notification
            sender.send(notification);

            // Mark as SENT
            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());

            notificationRepository.save(notification);

            log.info("Notification Sent Successfully");

        } catch (Exception e) {

            log.error("Error Processing Notification", e);

            // Update status only if notification was fetched successfully
            if (notification != null) {

                notification.setStatus(NotificationStatus.FAILED);

                notification.setRetryCount(
                        notification.getRetryCount() + 1);

                notification.setLastAttemptAt(
                        LocalDateTime.now());

                notificationRepository.save(notification);
            }
        }
    }
}