package com.icsd.boot.IcsdWallet.common;


public enum UserRole  {
    ROLE_ADMIN, ROLE_MANAGER, ROLE_CUSTOMER;

    public String getAuthority() {
        return name();
    }

}
