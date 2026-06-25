package com.swarnav.notification_platform.dto;

import com.swarnav.notification_platform.enums.NotificationType;
import lombok.Data;

@Data
public class CreateNotificationRequest {

    private String title;

    private String message;

    private String recipient;

    private NotificationType notificationType;

    private Long userId;
}
