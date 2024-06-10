package com.example.CampaignMarketing.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


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
    private List<String> considerations;
    private List<String> fav_foods;
    private List<String> cant_foods;
    private String activityArea;


}
