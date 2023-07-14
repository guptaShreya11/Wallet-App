package com.icsd.boot.IcsdWallet.entity;

import com.icsd.boot.IcsdWallet.anotation.ValueOfEnum;
import com.icsd.boot.IcsdWallet.common.Gender;
import com.icsd.boot.IcsdWallet.common.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Entity
@Data // Create getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity<Long> implements Serializable {


    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
   private List<UserRole> appUserRoles;

}
