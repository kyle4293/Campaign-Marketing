package com.example.CampaignMarketing.domain.user.service;


import com.example.CampaignMarketing.domain.user.dto.SignupRequestDto;
import com.example.CampaignMarketing.domain.user.dto.UpdateProfileRequestDto;
import com.example.CampaignMarketing.domain.user.dto.UserProfileDto;
import com.example.CampaignMarketing.domain.user.entity.Role;
import com.example.CampaignMarketing.domain.user.entity.User;
import com.example.CampaignMarketing.domain.user.repository.UserRepository;
import com.example.CampaignMarketing.global.exception.CustomException;
import com.example.CampaignMarketing.global.exception.ErrorCode;
import com.example.CampaignMarketing.global.s3.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileUploadService fileUploadService;

    private final String ADMIN_TOKEN = "wirafob32obvaobf3aaw1lk3ne";

    public UserProfileDto signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new CustomException(ErrorCode.EXISTED_USER_EMAIL);
        }

        // 사용자 ROLE 확인
        Role role = Role.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new CustomException(ErrorCode.INVALID_ADMIN_TOKEN);
            }
            role = Role.ADMIN;
        }

        // 사용자 등록
        User user = new User(email, password, requestDto.getUsername(), requestDto.getBirthDate(), requestDto.getGender(), requestDto.getImageUrl(), role);
        userRepository.save(user);

        return new UserProfileDto(user);
    }

    @Override
    public UserProfileDto updateUserProfile(User user, UpdateProfileRequestDto requestDto) {
        // Update user information from DTO
        user.setGender(requestDto.getGender());
        user.setBirthDate(requestDto.getBirthDate());
        user.setBlogUrl(requestDto.getBlogUrl());
        user.setBio(requestDto.getBio());
        user.setActivityArea(requestDto.getActivityArea());
        user.setImageUrl(requestDto.getImageUrl());


        if ("Other".equals(requestDto.getJob()) && requestDto.getCustomJob() != null) {
            user.setJob(requestDto.getCustomJob());
        } else {
            user.setJob(requestDto.getJob());
        }


        // Save the updated user
        User updatedUser = userRepository.save(user);

        // Return updated user profile DTO
        return new UserProfileDto(updatedUser);
    }

}
