package com.devdaph.ecommerce.security;

import com.devdaph.ecommerce.exception.NotFoundException;
import com.devdaph.ecommerce.repository.UserRepo;
import com.devdaph.ecommerce.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("user/Email Not Found"));
        return AuthUser.builder()
                .user(user)
                .build();
    }
}
