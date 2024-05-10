package com.example.CampaignMarketing.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Getter
@Setter
public class UpdateProfileRequestDto {

    private String username;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private String imageUrl;
    private String blogUrl;
    private String bio; //자기소개
    private String job;
    private String interest;
    private String activityArea;
    private String customJob;   //만약 job이 Other이면 job = customJob
    private String customInterest;

}
