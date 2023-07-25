package com.nexdew.wallet.service.impl;

import com.nexdew.wallet.common.enums.AccType;
import com.nexdew.wallet.common.enums.Gender;
import com.nexdew.wallet.configuration.exceptionconfig.CustomException;
import com.nexdew.wallet.dto.UserDto;
import com.nexdew.wallet.dto.request.AuthRequest;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.Account;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.repository.BankRepository;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.security.JwtTokenProvider;
import com.nexdew.wallet.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

import static com.nexdew.wallet.constants.ApiConstant.*;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private final ModelMapper mapper;
    private final BankRepository bankRepository;

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
    @Transactional
    public UserDto signUp(UserRequest userRequest) throws IllegalArgumentException {
        if (!userRepository.existsByUsername(userRequest.getUsername()) && !userRepository.existsByEmail(userRequest.getEmail())) {

            String description = userRequest.getDescription();
            AccType accType = AccType.valueOf(userRequest.getAccType());
            double openingBalance = userRequest.getOpeningBalance();

            User saveUser = User.builder()
                    .gender(Gender.valueOf(userRequest.getGender()))
                    .username(userRequest.getUsername())
                    .email(userRequest.getEmail())
                    .contact(userRequest.getContact())
                    .appUserRoles(userRequest.getAppUserRoles())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .build();

//            Random random = new Random();
//            int accountNumber;
//            String accNo;
//            String acc[]=new String[10];
//            for (int i=0;i<10;i++){
//                accountNumber=random.nextInt(10);
//                acc[i]=Integer.toString(accountNumber);
//            }
//            if(acc[0].equals(0)){
//                accNo = ((acc[0]+1) + acc[1] + acc[2] + acc[3] + acc[4] + acc[5] + acc[6] + acc[7] + acc[8] + acc[9]);
//
//            }
//            else {
//                accNo = (acc[0] + acc[1] + acc[2] + acc[3] + acc[4] + acc[5] + acc[6] + acc[7] + acc[8] + acc[9]);
//            }


            BigInteger accountNumber = bankRepository.findHighestAccountNumber();
            BigInteger addAcc = accountNumber.add(new BigInteger("1"));

            Account account = Account.builder()
                    .user(saveUser)
                    .accountNumber(addAcc)
                    .description(description)
                    .openingBalance(openingBalance)
                    .accType(accType)
                    .build();

            List<Account> accounts = new ArrayList<>();
            accounts.add(account);

            saveUser.setAccounts(accounts);

            userRepository.save(saveUser);


            UserDto userDto = this.mapper.map(saveUser, UserDto.class);
            return userDto;
        }

        throw new CustomException(DUPLICATE_USER);
    }

}
