package com.swarnav.notification_platform.dto;

import com.swarnav.notification_platform.enums.NotificationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {

    private Long id;

    private String title;

    private String recipient;

    private NotificationStatus status;

    private LocalDateTime createdAt;
}
