package com.backend.v1.dto.request;


//import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@Schema(description = "Profile request body")
public class ProfileRequestDto {

    @NotBlank(message = "Name must not be empty")
    @Schema(description = "User's full name", example = "U Tan")
    private String fullName;

    @NotBlank(message = "NRC must not be empty")
    @Pattern(
            regexp = "^(1[0-4]|[1-9])/[A-Za-z]+\\([A-Z]\\)\\d{6}$",
            message = "Invalid NRC format. Must be in the format 1-14/Word(A-Z)123456"
    )
    private String nrc;

    @NotBlank(message = "Contact must not be empty")
    @Pattern(
            regexp = "^09\\d{9}$",
            message = "Phone number must start with 09 and be exactly 11 digits"
    )
    private String contact;

    @NotNull(message = "Date of birth is required")
    @Schema(type = "string", format = "date", example = "1990-01-01")
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull
    @Valid
    private AddressRequestDto address;

    @NotNull
    @Valid
    private RegionRequestDto region;

    private String socialUrl;
}

