package com.nexdew.wallet.service;

import com.nexdew.wallet.dto.UserDto;
import com.nexdew.wallet.dto.request.AuthRequest;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.User;

public interface IAuthService {
    String signIn(AuthRequest authRequest);

    String signUp(User appUser);

    public UserDto signUp(UserRequest userRequest);
}
