package com.swarnav.notification_platform.service;
import java.time.LocalDateTime;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import com.swarnav.notification_platform.entity.Notification;
import com.swarnav.notification_platform.enums.NotificationStatus;
import com.swarnav.notification_platform.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {

    private final NotificationRepository notificationRepository;

    @Override
    public void onMessage(Message message, byte[] pattern) {

        try {
            String id = new String(message.getBody());
            Long notificationId = Long.parseLong(id);

            log.info("Received Notification ID : {}", notificationId);

            Notification notification = notificationRepository
                    .findById(notificationId)
                    .orElseThrow(() ->
                            new RuntimeException("Notification not found"));

            log.info("Processing Notification for {}",
                    notification.getRecipient());

            // Simulate Notification Sending
            Thread.sleep(2000);

            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());

            notificationRepository.save(notification);

            log.info("Notification Sent Successfully");

        } catch (Exception e) {

            log.error("Error Processing Notification", e);

        }

    }
}