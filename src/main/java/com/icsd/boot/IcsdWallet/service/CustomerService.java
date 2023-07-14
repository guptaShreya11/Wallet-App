package com.icsd.boot.IcsdWallet.service;

import com.icsd.boot.IcsdWallet.dto.CustomerDto;
import com.icsd.boot.IcsdWallet.dto.request.CustomerRequest;


import java.util.List;

public interface CustomerService {
    public CustomerDto createUser(CustomerRequest request);
    public CustomerDto search(String username);
    public List<CustomerDto> getAllCustomer();
    String delete(String username);


}
