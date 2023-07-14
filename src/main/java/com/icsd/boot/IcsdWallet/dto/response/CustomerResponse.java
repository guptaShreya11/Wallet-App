package com.icsd.boot.IcsdWallet.dto.response;


import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CustomerResponse {

    private String message;
    private Object data;
    private HttpStatus status;
}