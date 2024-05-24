package com.example.CampaignMarketing.domain.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequestDto {

    private Long marketId;
    private String title;
    private List<String> keywords;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> imageUrls;


}
