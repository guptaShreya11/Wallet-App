package com.nexdew.wallet.service.impl;

import com.nexdew.wallet.common.enums.Gender;
import com.nexdew.wallet.configuration.exceptionconfig.CustomException;
import com.nexdew.wallet.dto.UserDto;
import com.nexdew.wallet.dto.request.AuthRequest;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.security.JwtTokenProvider;
import com.nexdew.wallet.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.nexdew.wallet.constants.ApiConstant.*;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private final ModelMapper mapper;

    @Override
    public String signIn(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        return jwtTokenProvider.createToken(authRequest.getUsername(), userRepository.findByUsername(authRequest.getUsername()).getAppUserRoles());
    }

    @Override
    public String signUp(User appUser) {
        if (!userRepository.existsByUsername(appUser.getUsername())) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);
            return USER_CREATED;
        } else {
            return DUPLICATE_USER;
        }
    }

    @Override
    public UserDto signUp(UserRequest userRequest) throws IllegalArgumentException {
        if (!userRepository.existsByUsername(userRequest.getUsername()) && !userRepository.existsByEmail(userRequest.getEmail())) {
            User saveUser = User.builder()
                    .gender(Gender.valueOf(userRequest.getGender()))
                    .username(userRequest.getUsername())
                    .email(userRequest.getEmail())
                    .contact(userRequest.getContact())
                    .appUserRoles(userRequest.getAppUserRoles())
                    .password(passwordEncoder.encode(userRequest.getPassword())).build();
            User save = userRepository.save(saveUser);

            System.out.println(save);
            UserDto userDto = this.mapper.map(save, UserDto.class);
            return userDto;
        }

        throw new CustomException(DUPLICATE_USER);
    }

}
