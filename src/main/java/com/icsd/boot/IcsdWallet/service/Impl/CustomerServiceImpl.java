package com.icsd.boot.IcsdWallet.service.Impl;

import com.icsd.boot.IcsdWallet.common.Gender;
import com.icsd.boot.IcsdWallet.dto.CustomerDto;
import com.icsd.boot.IcsdWallet.dto.request.CustomerRequest;
import com.icsd.boot.IcsdWallet.entity.Customer;
import com.icsd.boot.IcsdWallet.repository.CustomerRepository;
import com.icsd.boot.IcsdWallet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl  implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDto createUser(CustomerRequest request) {
        Customer customer = Customer.builder().username(request.getUsername())
                .email(request.getEmail())
                .contact(request.getContact())
                .gender(Gender.valueOf(request.getGender()))
                .appUserRoles(request.getAppUserRoles())
                .password(request.getPassword()).build();
        System.out.println(customer);
        Customer saveCustomer = this.customerRepository.save(customer);
        CustomerDto customerDto = this.modelMapper.map(saveCustomer, CustomerDto.class);
        return  customerDto;
    }

    @Override
    public CustomerDto search(String username) {
        CustomerDto byUsername = this.customerRepository.findByUsername(username);
        return byUsername;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = this.customerRepository.findAll();
        return customers.stream().map(customer -> modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
    }

    @Override
    public String delete(String username) {

        this.customerRepository.deleteByUsername(username);
     return "Customer Deleted Successfully";
    }



}
