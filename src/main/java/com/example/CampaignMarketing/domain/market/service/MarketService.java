package com.example.CampaignMarketing.domain.market.service;


import com.example.CampaignMarketing.domain.campaign.dto.CampaignResponseDto;
import com.example.CampaignMarketing.domain.campaign.entity.QCampaign;
import com.example.CampaignMarketing.domain.campaign.repository.CampaignRepository;
import com.example.CampaignMarketing.domain.market.dto.MarketRequestDto;
import com.example.CampaignMarketing.domain.market.dto.MarketResponseDto;
import com.example.CampaignMarketing.domain.market.entity.Market;
import com.example.CampaignMarketing.domain.market.entity.QMarket;
import com.example.CampaignMarketing.domain.market.repository.MarketRepository;
import com.example.CampaignMarketing.domain.user.entity.User;
import com.example.CampaignMarketing.global.exception.CustomException;
import com.example.CampaignMarketing.global.exception.ErrorCode;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MarketService {

    private final MarketRepository marketRepository;
    private final CampaignRepository campaignRepository;

    public MarketResponseDto createMarket(User user, MarketRequestDto requestDto) {
        // RequestDto -> Entity
        Market market = new Market(user, requestDto);
        // DB 저장
        Market saveMarket = marketRepository.save(market);

        return new MarketResponseDto(saveMarket);
    }

    public Page<MarketResponseDto> getMarkets(User user, int page, int size, String sortBy, boolean isAsc, String keyword) {
        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        QMarket qMarket = QMarket.market;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qMarket.user.eq(user));

        if (keyword != null && !keyword.isEmpty()) {
            builder.and(qMarket.companyName.containsIgnoreCase(keyword)
                    .or(qMarket.description.containsIgnoreCase(keyword)));
        }

        return marketRepository.findAll(builder, pageable).map(MarketResponseDto::new);

    }

    public MarketResponseDto getMarketDto(Long id) {
        Market market = findMarket(id);
        MarketResponseDto marketResponseDto = new MarketResponseDto(market);
        return marketResponseDto;
    }

    private Market findMarket(Long id) {
        return marketRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_MARKET)
        );
    }

    public Page<CampaignResponseDto> getCampaignsByMarketId(User user, Long marketId, int page, int size, String sortBy, boolean isAsc, String keyword) {
        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        QCampaign qCampaign = QCampaign.campaign;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCampaign.market.id.eq(marketId));
        builder.and(qCampaign.market.user.eq(user));

        if (keyword != null && !keyword.isEmpty()) {
            builder.and(qCampaign.title.containsIgnoreCase(keyword)
                    .or(qCampaign.description.containsIgnoreCase(keyword)));
        }

        return campaignRepository.findAll(builder, pageable).map(CampaignResponseDto::new);
    }

    public void deleteMarket(User user, Long id) {
        Market market = findMarket(id);

        if(market.getUser().getId().equals(user.getId())) {
            marketRepository.delete(market);
        } else {
            throw new CustomException(ErrorCode.REQUIRED_ADMIN_USER_AUTHORITY);
        }
    }
}
