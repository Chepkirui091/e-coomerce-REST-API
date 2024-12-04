package com.devdaph.ecommerce.service.impl;

import com.devdaph.ecommerce.dto.LoginRequest;
import com.devdaph.ecommerce.dto.Response;
import com.devdaph.ecommerce.dto.UserDto;
import com.devdaph.ecommerce.entity.User;
import com.devdaph.ecommerce.mapper.EntityDtoMapper;
import com.devdaph.ecommerce.repository.UserRepo;
import com.devdaph.ecommerce.security.JwtUtils;
import com.devdaph.ecommerce.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private  final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response registerUSer(UserDto registrationRequest) {
        return null;
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Response getAllUsers() {
        return null;
    }

    @Override
    public User getLoginUser() {
        return null;
    }

    @Override
    public Response getUserInfoAndOrderHistory() {
        return null;
    }
}
