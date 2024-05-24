package com.example.CampaignMarketing.domain.market.dto;


import com.example.CampaignMarketing.domain.market.entity.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketResponseDto {

    private Long id;
    private String companyName;
    private String businessType;
    private String phone;
    private String address;
    private String description;
    private List<String> keywords;
    private List<String> imageUrls;

    public MarketResponseDto(Market market) {
        this.id = market.getId();
        this.companyName = market.getCompanyName();
        this.businessType = market.getBusinessType();
        this.phone = market.getPhone();
        this.address = market.getAddress();
        this.description = market.getDescription();
        this.keywords = market.getKeywords();
        this.imageUrls = market.getImageUrls();
    }
}
