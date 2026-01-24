package com.backend.v1.dto.request;

import lombok.Data;

@Data
public class UserCreatedRequestDto {
    private String email;
    private String password;
    private String Role;
}
