package com.backend.v1.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String email;

    private String role;

    private boolean hasProfile;
}
