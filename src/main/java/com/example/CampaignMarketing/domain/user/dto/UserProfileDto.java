package com.example.CampaignMarketing.domain.user.dto;



import com.example.CampaignMarketing.domain.user.entity.Role;
import com.example.CampaignMarketing.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserProfileDto {
    Long userId;
    String username;
    String email;
    String imageUrl;
    LocalDate birthDate;
    String gender;
    String bio;
    String blogUrl;
    String activityArea;
    String interest;
    String job;

    public UserProfileDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
        this.birthDate = user.getBirthDate();
        this.gender = user.getGender();
        this.activityArea = user.getActivityArea();
        this.bio = user.getBio();
        this.blogUrl = user.getBlogUrl();
        this.interest = user.getInterest();
        this.job = user.getJob();
    }

}