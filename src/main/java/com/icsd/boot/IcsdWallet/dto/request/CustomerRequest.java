package com.icsd.boot.IcsdWallet.dto.request;


import com.icsd.boot.IcsdWallet.anotation.ValueOfEnum;
import com.icsd.boot.IcsdWallet.common.Gender;
import com.icsd.boot.IcsdWallet.common.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {


    @NotNull(message = "First name can not be null")
    @NotBlank(message = "Firstname can not be blank")
    private String username;

    @NotNull(message = "Email can not be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message="Email address must be valid")
    @NotBlank(message = "Email can be blank")
    private String email;


    private String contact;

    @ValueOfEnum(enumClass = Gender.class)
    private String gender;


    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,20}$",message = "Password have min 6 and max 20 character along with 1 capital ,1 small 1 numeric and 1 special character")
    @NotBlank(message = "Password can not be blank ")
    private String password;

   private List<UserRole> appUserRoles;

}
