package com.example.CampaignMarketing.domain.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketRequestDto {

    private String category;
    private String address;
    private String name;
    private String description;
    private String wayDescription;

    private List<String> imageUrls; // 이미지 URL 리스트 추가

}
