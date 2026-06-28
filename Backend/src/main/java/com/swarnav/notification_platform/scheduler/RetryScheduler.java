package com.swarnav.notification_platform.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.swarnav.notification_platform.entity.Notification;
import com.swarnav.notification_platform.enums.NotificationStatus;
import com.swarnav.notification_platform.repository.NotificationRepository;
import com.swarnav.notification_platform.service.RedisPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RetryScheduler {

    private final NotificationRepository notificationRepository;
    private final RedisPublisher redisPublisher;

    private static final int MAX_RETRY = 3;

    @Scheduled(fixedDelay = 30000)
    public void retryFailedNotifications() {

        log.info("Checking for failed notifications...");

        List<Notification> failedNotifications =
                notificationRepository.findByStatus(
                        NotificationStatus.FAILED);

        for (Notification notification : failedNotifications) {

            if (notification.getRetryCount() < MAX_RETRY) {

                log.info("Retrying Notification {}",
                        notification.getId());

                redisPublisher.publish(
                        String.valueOf(notification.getId()));

            }

        }

    }

}
