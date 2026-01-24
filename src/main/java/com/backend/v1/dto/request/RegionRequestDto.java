package com.backend.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionRequestDto {

    @NotBlank(message = "Region name is required")
    private String name;
}

