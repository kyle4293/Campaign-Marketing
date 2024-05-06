package com.example.CampaignMarketing.domain.market.entity;


import com.example.CampaignMarketing.domain.campaign.entity.Campaign;
import com.example.CampaignMarketing.domain.market.dto.MarketRequestDto;
import com.example.CampaignMarketing.domain.user.entity.User;
import com.example.CampaignMarketing.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "market")
public class Market extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "market_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String category;

    private Long ownerId;

    private String address;

    private String name;

    @Comment("가게 한 줄 소개")
    private String description;



    // 이미지 URL을 저장하는 리스트
    @ElementCollection
    @CollectionTable(name = "market_images", joinColumns = @JoinColumn(name = "market_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    @OneToMany(mappedBy = "market")
    private List<Campaign> campaigns = new ArrayList<>();

    @Builder
    public Market(String category, Long ownerId, String address, String name, String description) {
        this.category = category;
        this.ownerId = ownerId;
        this.address = address;
        this.name = name;
        this.description = description;
    }

    public Market(User user, MarketRequestDto requestDto) {
        this.user = user;
        this.category = requestDto.getCategory();
        this.address = requestDto.getAddress();
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.imageUrls = requestDto.getImageUrls();
    }
}
