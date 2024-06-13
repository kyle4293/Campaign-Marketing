package com.example.CampaignMarketing.domain.campaign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignRecommendResponseDto {

    private List<Long> ids;
}
