package com.swarnav.notification_platform.service.notification;

import com.swarnav.notification_platform.entity.Notification;

public interface NotificationSender {

    void send(Notification notification);

}