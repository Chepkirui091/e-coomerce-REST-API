package com.devdaph.ecommerce.service.interf;

import com.devdaph.ecommerce.dto.LoginRequest;
import com.devdaph.ecommerce.dto.Response;
import com.devdaph.ecommerce.dto.UserDto;
import com.devdaph.ecommerce.entity.User;

public interface UserService {

    Response registerUSer(UserDto registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    User getLoginUser();
    Response getUserInfoAndOrderHistory();

}
