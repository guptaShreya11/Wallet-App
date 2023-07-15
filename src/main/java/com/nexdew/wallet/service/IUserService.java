package com.nexdew.wallet.service;

import com.nexdew.wallet.dto.CustomerDto;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

    public CustomerDto createUser(UserRequest request);
    void delete(String username);
    User search(String username);
    User whoami(HttpServletRequest req);
    String refresh(String username);
}
