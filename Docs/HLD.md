# Distributed Real-Time Notification Platform

Build a scalable notification platform capable of delivering real-time notifications to users using WebSockets and Redis Pub/Sub.

## Functional Requirements

1. User Registration
2. User Login
3. Create Notification
4. View Notifications
5. Mark Notification As Read
6. Real-Time Notification Delivery
7. Notification Preferences
8. Offline Notification Recovery

## Non Functional Requirements

1. Low Latency
2. Horizontal Scalability
3. Reliability
4. High Availability
5. Fault Tolerance

## High Level Architecture

Producer Services

      ↓

Notification Service

      ↓

    MySQL

      ↓

Redis Pub/Sub

      ↓

   WebSocket

      ↓

  Frontend
