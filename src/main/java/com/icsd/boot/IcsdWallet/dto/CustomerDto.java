package com.icsd.boot.IcsdWallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto
{
    private String customer_Id;
    private String username;
    private String contact;
    private String email;
    private String gender;
    private String appUserRoles;

}