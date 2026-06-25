package com.swarnav.notification_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swarnav.notification_platform.entity.Notification;
import com.swarnav.notification_platform.enums.NotificationStatus;
import com.swarnav.notification_platform.enums.NotificationType;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findByStatus(
            NotificationStatus status);

    List<Notification> findByNotificationType(
            NotificationType notificationType);
}
