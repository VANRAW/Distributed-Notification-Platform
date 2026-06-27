package com.swarnav.notification_platform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String CHANNEL = "notification-channel";

    public void publish(String message) {

        redisTemplate.convertAndSend(CHANNEL, message);

        System.out.println("Published to Redis: " + message);
    }
}