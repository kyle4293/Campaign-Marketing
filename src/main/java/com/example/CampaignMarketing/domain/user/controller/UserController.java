package com.example.CampaignMarketing.domain.user.controller;

import com.example.CampaignMarketing.domain.user.dto.SignupRequestDto;
import com.example.CampaignMarketing.domain.user.dto.UpdateProfileRequestDto;
import com.example.CampaignMarketing.domain.user.dto.UserProfileDto;
import com.example.CampaignMarketing.domain.user.service.UserServiceImpl;
import com.example.CampaignMarketing.global.response.ApiResponse;
import com.example.CampaignMarketing.global.response.SuccessCode;
import com.example.CampaignMarketing.global.s3.FileUploadService;
import com.example.CampaignMarketing.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final FileUploadService fileUploadService;

    public UserController(UserServiceImpl userService, FileUploadService fileUploadService) {
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        UserProfileDto dto = userService.signup(requestDto);
        SuccessCode successCode = SuccessCode.SUCCESS_USER_SIGN_UP;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), dto));
    }

    // 프로필 수정
    @PutMapping(value = "/profile")
    @ResponseBody
    public ResponseEntity<ApiResponse> updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute UpdateProfileRequestDto requestDto) throws IOException {
        String imageUrl = null;
        if (requestDto.getImageUrl() != null && !requestDto.getImageUrl().isEmpty()) {
            imageUrl = fileUploadService.uploadFile(requestDto.getImageUrl());
        }

        UserProfileDto dto = userService.updateUserProfile(userDetails.getUser(), requestDto, imageUrl);
        SuccessCode successCode = SuccessCode.SUCCESS_UPDATE_USER_INFO;
        return ResponseEntity.status(successCode.getHttpStatus())
                .body(ApiResponse.of(successCode.getCode(), successCode.getMessage(), dto));
    }

    // 프로필 조회
    @GetMapping("/profile")
    @ResponseBody
    public UserProfileDto getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new UserProfileDto(userDetails.getUser());
    }

}