package com.backend.v1.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Profile response body")
public class ProfileResponseDto {

    @Schema(description = "Profile ID", example = "1")
    private Long id;

    @Schema(description = "User ID", example = "10")
    private Long userId;

    @Schema(description = "User full name", example = "U Tan")
    private String fullName;

    @Schema(
            description = "User NRC",
            example = "12/Takana(N)123456"
    )
    private String nrc;

    @Schema(description = "User phone number", example = "09123456789")
    private String contact;

    @Schema(description = "Date of birth", format = "date", example = "1990-01-01")
    private LocalDate dob;

    @Schema(description = "Gender", example = "MALE")
    private String gender;

    @Schema(description = "Social profile URL", example = "https://github.com/YeZawHlaing/")
    private String socialUrl;

    @Schema(description = "Profile picture URL", example = "https://cdn.app.com/profile.jpg")
    private String profilePic;

    @Schema(description = "Current Address", example = "University of Computer Studies, Mandalay")
    private String address;

    @Schema(description = "Region name", example = "Yangon")
    private String region;

}

