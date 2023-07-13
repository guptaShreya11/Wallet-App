package com.icsd.boot.IcsdWallet.entity;

import com.icsd.boot.IcsdWallet.common.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Entity
@Data // Create getters and setters
@NoArgsConstructor
public class Customer extends BaseEntity<Long> implements Serializable {


    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;


    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> userRoles;

}
