package com.example.CampaignMarketing.domain.campaign.service;


import com.example.CampaignMarketing.domain.campaign.dto.CampaignRequestDto;
import com.example.CampaignMarketing.domain.campaign.dto.CampaignResponseDto;
import com.example.CampaignMarketing.domain.campaign.entity.Campaign;
import com.example.CampaignMarketing.domain.campaign.repository.CampaignRepository;
import com.example.CampaignMarketing.domain.market.entity.Market;
import com.example.CampaignMarketing.domain.market.entity.QMarket;
import com.example.CampaignMarketing.domain.market.repository.MarketRepository;
import com.example.CampaignMarketing.domain.user.entity.User;
import com.example.CampaignMarketing.global.exception.CustomException;
import com.example.CampaignMarketing.global.exception.ErrorCode;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final MarketRepository marketRepository;


    public CampaignResponseDto createCampaign(User user, CampaignRequestDto requestDto) {
        Market market = findMarket(requestDto.getMarketId());

        Campaign campaign = new Campaign(market, requestDto);
        // DB 저장
        Campaign saveCampaign = campaignRepository.save(campaign);

        return new CampaignResponseDto(saveCampaign);

//        System.out.println("market = " + market);
//        System.out.println("market.getUser() = " + market.getUser());
//        System.out.println("user = " + user);

//        if (market.getUser().equals(user)) {
//            // RequestDto -> Entity
//            Campaign campaign = new Campaign(market, requestDto);
//            // DB 저장
//            Campaign saveCampaign = campaignRepository.save(campaign);
//
//            return new CampaignResponseDto(saveCampaign);
//        } else {
//            throw new CustomException(ErrorCode.INVALID_ADMIN_TOKEN);
//        }

    }

    public Page<CampaignResponseDto> getCampaigns(int page, int size, String sortBy, boolean isAsc, String keyword) {
        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
//        System.out.println("keyword = " + keyword);

        if (keyword != null && !keyword.isEmpty()) {
            QMarket qMarket = QMarket.market;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qMarket.name.containsIgnoreCase(keyword)
                    .or(qMarket.description.containsIgnoreCase(keyword)));
            return campaignRepository.findAll(builder, pageable).map(CampaignResponseDto::new);
        } else {
            return campaignRepository.findAll(pageable).map(CampaignResponseDto::new);
        }
    }

    public CampaignResponseDto getCampaignDto(Long id) {
        Campaign campaign = findCampaign(id);
        CampaignResponseDto campaignResponseDto = new CampaignResponseDto(campaign);
        return campaignResponseDto;
    }

    private Campaign findCampaign(Long id) {
        return campaignRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_CAMPAIGN)
        );
    }

    private Market findMarket(Long id) {
        return marketRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_MARKET)
        );
    }

    public Page<CampaignResponseDto> getRecommendedCampaigns() {
        Pageable pageable = PageRequest.of(0, 3);
        return campaignRepository.findAll(pageable).map(CampaignResponseDto::new);
    }


}
