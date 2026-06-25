package com.swarnav.notification_platform.controller;

import com.swarnav.notification_platform.dto.CreateNotificationRequest;
import com.swarnav.notification_platform.dto.NotificationResponse;
import com.swarnav.notification_platform.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponse createNotification(
            @RequestBody CreateNotificationRequest request){

        return notificationService.createNotification(request);
    }
}