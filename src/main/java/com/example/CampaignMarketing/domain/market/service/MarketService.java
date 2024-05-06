package com.example.CampaignMarketing.domain.market.service;


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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MarketService {

    private final MarketRepository marketRepository;


    public MarketResponseDto createMarket(User user, MarketRequestDto requestDto) {
        // RequestDto -> Entity
        Market market = new Market(user, requestDto);
        // DB 저장
        Market saveMarket = marketRepository.save(market);

        return new MarketResponseDto(saveMarket);
    }

    public Page<MarketResponseDto> getMarkets(int page, int size, String sortBy, boolean isAsc, String keyword) {
        Sort sort = Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
//        System.out.println("keyword = " + keyword);

        if (keyword != null && !keyword.isEmpty()) {
            QMarket qMarket = QMarket.market;
            BooleanBuilder builder = new BooleanBuilder();
            builder.and(qMarket.name.containsIgnoreCase(keyword)
                    .or(qMarket.description.containsIgnoreCase(keyword)));
            return marketRepository.findAll(builder, pageable).map(MarketResponseDto::new);
        } else {
            return marketRepository.findAll(pageable).map(MarketResponseDto::new);
        }
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
}
