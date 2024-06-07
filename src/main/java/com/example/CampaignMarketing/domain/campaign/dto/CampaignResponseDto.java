package com.example.CampaignMarketing.domain.campaign.dto;

import com.example.CampaignMarketing.domain.campaign.entity.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignResponseDto {

    //market info
    private String marketName;
    private String address;
    private String detailAddress;

    //campaign info
    private Long id;
    private String title;
    private List<String> keywords;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> imageUrls;

    public CampaignResponseDto(Campaign campaign) {
        this.marketName = campaign.getMarket().getCompanyName();
        this.address = campaign.getMarket().getAddress();
        this.detailAddress = campaign.getMarket().getDetailAddress();
        this.id = campaign.getId();
        this.title = campaign.getTitle();
        this.keywords = campaign.getKeywords();
        this.description = campaign.getDescription();
        this.startDate = campaign.getStartDate();
        this.endDate = campaign.getEndDate();
        this.imageUrls = campaign.getImageUrls();
    }
}
