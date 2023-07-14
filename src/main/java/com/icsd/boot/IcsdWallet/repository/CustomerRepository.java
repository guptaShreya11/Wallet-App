package com.icsd.boot.IcsdWallet.repository;

import com.icsd.boot.IcsdWallet.dto.CustomerDto;
import com.icsd.boot.IcsdWallet.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer,Integer>{

//    boolean exitByUsername(String username);

    CustomerDto findByUsername(String username);

    void deleteByUsername(String username);

}
