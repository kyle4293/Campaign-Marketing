package com.example.CampaignMarketing.domain.user.entity;


import com.example.CampaignMarketing.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(nullable = false)
    private String username;

    private LocalDate birthDate;

    private String gender;

    private String imageUrl;
    private String blogUrl;
    private String bio; //자기소개
    private String job;
    private String interest;
    private String activityArea;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String provider;

    public User(String email, String password, String username, LocalDate birthDate, String gender, String imageUrl ,Role role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
        this.imageUrl = imageUrl;
    }
}
