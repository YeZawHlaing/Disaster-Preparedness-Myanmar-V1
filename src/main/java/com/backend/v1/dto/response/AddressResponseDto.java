package com.backend.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {

    private String city;
    private String township;
    private String road;
    private String street;

    @Schema(description = "Current coordinates", example = "latitude:0.234545,longitude:1.4567325")
    private CoordinatesResponseDto coordinates;
}

