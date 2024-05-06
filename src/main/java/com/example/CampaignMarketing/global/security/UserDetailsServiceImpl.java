package com.example.CampaignMarketing.global.security;


import com.example.CampaignMarketing.domain.user.entity.User;
import com.example.CampaignMarketing.domain.user.repository.UserRepository;
import com.example.CampaignMarketing.global.exception.CustomException;
import com.example.CampaignMarketing.global.exception.ErrorCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_FOR_LOGIN));

        return new UserDetailsImpl(user);
    }
}