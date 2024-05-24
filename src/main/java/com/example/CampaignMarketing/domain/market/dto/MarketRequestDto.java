package com.example.CampaignMarketing.domain.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketRequestDto {

    private String businessCertificate;
    private String companyName;
    private String businessType;
    private String phone;
    private String address;
    private String description;
    private List<String> keywords;
    private List<String> imageUrls;

}
