package com.swarnav.notification_platform.service;

import com.swarnav.notification_platform.dto.CreateNotificationRequest;
import com.swarnav.notification_platform.dto.NotificationResponse;
import com.swarnav.notification_platform.entity.Notification;
import com.swarnav.notification_platform.entity.User;
import com.swarnav.notification_platform.enums.NotificationStatus;
import com.swarnav.notification_platform.repository.NotificationRepository;
import com.swarnav.notification_platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationResponse createNotification(
            CreateNotificationRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Notification notification = Notification.builder()
                .title(request.getTitle())
                .message(request.getMessage())
                .recipient(request.getRecipient())
                .notificationType(request.getNotificationType())
                .status(NotificationStatus.PENDING)
                .user(user)
                .build();

        notification = notificationRepository.save(notification);

        return NotificationResponse.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .recipient(notification.getRecipient())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
