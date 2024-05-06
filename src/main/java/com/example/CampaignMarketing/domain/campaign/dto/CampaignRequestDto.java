package com.example.CampaignMarketing.domain.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequestDto {

    private Long marketId;
    private String title;
    private String keyword;
    private String description;
    private String gender;
    private List<String> age;
    private List<String> job;
    private List<String> imageUrls; // 이미지 URL 리스트 추가


}
