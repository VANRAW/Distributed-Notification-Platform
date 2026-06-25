package com.swarnav.notification_platform.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL
    )
    private List<Notification> notifications;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
