package com.backend.v1.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {

    private String city;
    private String township;
    private String road;
    private String street;
}

