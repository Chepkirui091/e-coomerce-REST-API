package com.devdaph.ecommerce.service.impl;

import com.devdaph.ecommerce.dto.LoginRequest;
import com.devdaph.ecommerce.dto.Response;
import com.devdaph.ecommerce.dto.UserDto;
import com.devdaph.ecommerce.entity.User;
import com.devdaph.ecommerce.enums.UserRole;
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
        UserRole role = UserRole.USER;

        if (registrationRequest.getRole() != null && registrationRequest.getRole().equalsIgnoreCase("admin")) {
            role = UserRole.ADMIN;
        }

        User user = User.builder().build()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .password(registrationRequest.getPassword())
                .role(role)
                .build();
        User savedUSer = userRepo.save(user);

        UserDto userDto = entityDtoMapper.mapUSerToUserDto(savedUSer);

        return Response.builder()
                .status(200)
                .message("USer Successfully Added")
                .user(userDto)
                .build();

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
