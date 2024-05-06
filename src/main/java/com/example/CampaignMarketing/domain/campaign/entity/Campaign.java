package com.example.CampaignMarketing.domain.campaign.entity;


import com.example.CampaignMarketing.domain.campaign.dto.CampaignRequestDto;
import com.example.CampaignMarketing.domain.market.entity.Market;
import com.example.CampaignMarketing.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "campaign")
public class Campaign extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    private String title;
    private String keyword;
    private String description;
    private String gender;
    private List<String> age;
    private List<String> job;
    @ElementCollection
    @CollectionTable(name = "campaign_images", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    public Campaign(Market market, CampaignRequestDto requestDto) {
        this.market = market;
        this.title = requestDto.getTitle();
        this.keyword = requestDto.getKeyword();
        this.description = requestDto.getDescription();
        this.gender = requestDto.getGender();
        this.age = requestDto.getAge();
        this.job = requestDto.getJob();
        this.imageUrls = requestDto.getImageUrls();
    }

}
