package com.nexdew.wallet;

import java.util.ArrayList;
import java.util.Arrays;

import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.common.enums.UserRole;
import com.nexdew.wallet.service.IAuthService;
import com.nexdew.wallet.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class WalletApplication implements CommandLineRunner {

  private final IAuthService authService;

  public static void main(String[] args) {
    SpringApplication.run(WalletApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {
    User admin = new User();
    admin.setUsername("admin");
    admin.setPassword("admin@123");
    admin.setEmail("admin@email.com");
    admin.setAppUserRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_ADMIN)));

    authService.signUp(admin);
  }

}
