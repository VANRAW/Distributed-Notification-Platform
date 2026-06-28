package com.swarnav.notification_platform.service.notification;

import org.springframework.stereotype.Component;

import com.swarnav.notification_platform.enums.NotificationType;

@Component
public class NotificationSenderFactory {

    private final EmailNotificationSender emailSender;
    private final SmsNotificationSender smsSender;
    private final PushNotificationSender pushSender;
    private final InAppNotificationSender inAppSender;

    public NotificationSenderFactory(
            EmailNotificationSender emailSender,
            SmsNotificationSender smsSender,
            PushNotificationSender pushSender,
            InAppNotificationSender inAppSender) {

        this.emailSender = emailSender;
        this.smsSender = smsSender;
        this.pushSender = pushSender;
        this.inAppSender = inAppSender;
    }

    public NotificationSender getSender(NotificationType type) {

        switch (type) {

            case EMAIL:
                return emailSender;

            case SMS:
                return smsSender;

            case PUSH:
                return pushSender;

            case IN_APP:
                return inAppSender;

            default:
                throw new IllegalArgumentException(
                        "Unsupported Notification Type");
        }
    }
}
