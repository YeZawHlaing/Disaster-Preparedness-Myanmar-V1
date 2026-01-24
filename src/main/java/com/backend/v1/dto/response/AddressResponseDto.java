package com.backend.v1.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {

    private String city;
    private String township;
    private String road;
    private String street;
}

