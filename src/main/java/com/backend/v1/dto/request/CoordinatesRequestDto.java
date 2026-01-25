package com.backend.v1.dto.request;

import com.backend.v1.dto.response.AddressResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatesRequestDto {

    private double latitude;

    private double longitude;

//    private AddressRequestDto addressRequestDto;

}
