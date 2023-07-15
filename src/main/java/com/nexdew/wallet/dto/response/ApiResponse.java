package com.nexdew.wallet.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ApiResponse {

    private String message;
    private Object data;
    private HttpStatus status;
}
