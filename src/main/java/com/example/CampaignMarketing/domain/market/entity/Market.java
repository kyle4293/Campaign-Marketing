package com.example.CampaignMarketing.domain.market.entity;

import com.example.CampaignMarketing.domain.campaign.entity.Campaign;
import com.example.CampaignMarketing.domain.market.dto.MarketRequestDto;
import com.example.CampaignMarketing.domain.user.entity.User;
import com.example.CampaignMarketing.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "market")
public class Market extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String businessCertificate;
    private String companyName;
    private String businessType;
    private String phone;
    private String address;
    private String detailAddress;
    private String description;
    private List<String> keywords;

    @ElementCollection
    @CollectionTable(name = "market_images", joinColumns = @JoinColumn(name = "market_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    @OneToMany(mappedBy = "market")
    private List<Campaign> campaigns = new ArrayList<>();

    public Market(User user, MarketRequestDto requestDto) {
        this.user = user;
        this.businessCertificate = requestDto.getBusinessCertificate();
        this.companyName = requestDto.getCompanyName();
        this.businessType = requestDto.getBusinessType();
        this.phone = requestDto.getPhone();
        this.address = requestDto.getAddress();
        this.detailAddress = requestDto.getDetailAddress();
        this.description = requestDto.getDescription();
        this.keywords = requestDto.getKeywords();
        this.imageUrls = requestDto.getImageUrls();
    }
}
