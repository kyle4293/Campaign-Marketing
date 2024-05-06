package com.example.CampaignMarketing.domain.user.service;


import com.example.CampaignMarketing.domain.user.dto.SignupRequestDto;
import com.example.CampaignMarketing.domain.user.dto.UpdateProfileRequestDto;
import com.example.CampaignMarketing.domain.user.dto.UserProfileDto;
import com.example.CampaignMarketing.domain.user.entity.User;

public interface UserService {

    // 회원 가입
    UserProfileDto signup(SignupRequestDto requestDto);


    // 프로필 수정
    UserProfileDto updateUserProfile(User user, UpdateProfileRequestDto requestDto, String imageUrl);
}