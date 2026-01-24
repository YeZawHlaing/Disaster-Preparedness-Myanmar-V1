package com.backend.v1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllUserRequest {

    private int page = 0;
    private int size = 10;

    private String sortBy = "id";
    private String sortDirection = "DESC";

    private Long roleId;
    private String email;
}
