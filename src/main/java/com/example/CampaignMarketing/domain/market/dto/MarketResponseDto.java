package com.example.CampaignMarketing.domain.market.dto;


import com.example.CampaignMarketing.domain.market.entity.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MarketResponseDto {

    private Long id;
    private String category;
    private Long ownerId;
    private String name;
    private String description;

    public MarketResponseDto(Market market) {
        this.id = market.getId();
        this.category = market.getCategory();
        this.ownerId = market.getOwnerId();
        this.name = market.getName();
        this.description = market.getDescription();
    }
}
