package com.example.CampaignMarketing.domain.market.controller;


import com.example.CampaignMarketing.domain.campaign.dto.CampaignResponseDto;
import com.example.CampaignMarketing.domain.market.dto.MarketRequestDto;
import com.example.CampaignMarketing.domain.market.dto.MarketResponseDto;
import com.example.CampaignMarketing.domain.market.service.MarketService;
import com.example.CampaignMarketing.domain.user.entity.User;
import com.example.CampaignMarketing.global.s3.FileUploadService;
import com.example.CampaignMarketing.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/markets")
public class MarketController {

    private final MarketService marketService;
    private final FileUploadService fileUploadService;


    @PostMapping(consumes = "multipart/form-data")
    public MarketResponseDto createMarket(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestPart("marketData") MarketRequestDto requestDto,
                                          @RequestPart("businessCertificate") MultipartFile businessCertificate,
                                          @RequestPart(value = "companyPhotos", required = false) List<MultipartFile> files) throws IOException {
        User user = userDetails.getUser();

        String certificateFile = fileUploadService.uploadFile(businessCertificate);
        requestDto.setBusinessCertificate(certificateFile);

        List<String> imageUrls = null;
        if (files != null && !files.isEmpty()) {
            imageUrls = fileUploadService.uploadFiles(files); // 파일 업로드 서비스 호출
            requestDto.setImageUrls(imageUrls);
        }
        return marketService.createMarket(user, requestDto);
    }

    @GetMapping
    public Page<MarketResponseDto> getMarkets(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                @RequestParam(value = "keyword", required = false) String keyword,
                                                @RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size,
                                                @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
                                                @RequestParam(name = "isAsc", defaultValue = "true") boolean isAsc) {
        User user = userDetails.getUser();

        // 서비스 메소드 호출시 페이지 번호 조정
        return marketService.getMarkets(user, page - 1, size, sortBy, isAsc, keyword);
    }

    @GetMapping("/{id}")
    public MarketResponseDto getMarketDto(@PathVariable Long id) {
        return marketService.getMarketDto(id);
    }

    @GetMapping("/{id}/campaigns")
    public Page<CampaignResponseDto> getCampaignsByMarketId(@AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "endDate") String sortBy,
            @RequestParam(name = "isAsc", defaultValue = "false") boolean isAsc) {
        User user = userDetails.getUser();

        return marketService.getCampaignsByMarketId(user, id, page, size, sortBy, isAsc, keyword);
    }

    @DeleteMapping("/{id}")
    public void deleteMarket(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        User user = userDetails.getUser();
        marketService.deleteMarket(user, id);
    }

}
