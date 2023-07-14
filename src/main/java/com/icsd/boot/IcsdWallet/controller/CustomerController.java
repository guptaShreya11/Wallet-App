package com.icsd.boot.IcsdWallet.controller;

import com.icsd.boot.IcsdWallet.constants.ApiConstants;
import com.icsd.boot.IcsdWallet.dto.request.CustomerRequest;
import com.icsd.boot.IcsdWallet.dto.response.CustomerResponse;
import com.icsd.boot.IcsdWallet.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/wallet")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request){
        return  ResponseEntity.ok(new CustomerResponse(ApiConstants.USER_CREATED, customerService.createUser(request), HttpStatus.CREATED));
    }
}
