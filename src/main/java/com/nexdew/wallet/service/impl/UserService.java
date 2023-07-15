package com.nexdew.wallet.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.nexdew.wallet.common.enums.Gender;
import com.nexdew.wallet.dto.UserDto;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.security.JwtTokenProvider;
import com.nexdew.wallet.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  private final ModelMapper modelMapper;

  @Override
  public UserDto createUser(UserRequest request) {
    User user = User.builder().username(request.getUsername())
            .email(request.getEmail())
            .contact(request.getContact())
            .gender(Gender.valueOf(request.getGender()))
            .appUserRoles(request.getAppUserRoles())
            .password(request.getPassword()).build();
    User saveUser = this.userRepository.save(user);
    UserDto userDto = this.modelMapper.map(saveUser, UserDto.class);
    return  userDto;
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public User search(String username) {
    User appUser = userRepository.findByUsername(username);
    return appUser;
  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
  }

}
