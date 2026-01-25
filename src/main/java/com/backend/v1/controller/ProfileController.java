package com.backend.v1.controller;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.request.ProfileRequestDto;
import com.backend.v1.service.ProfileService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Profile")
@CrossOrigin
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


@PostMapping("/{userId}")
public ResponseEntity<?> createProfile(
        @PathVariable Long userId,
        @Valid @RequestBody ProfileRequestDto dto
) {
    System.out.println("DTO CONTACT = " + dto.getContact());
    System.out.println("DTO FULL NAME = " + dto.getFullName());

    return ResponseEntity.ok(profileService.createProfile(userId, dto));
}

    @GetMapping("/{userId}")
    public ApiResponse getProfileByUserId(
            @PathVariable Long userId
    ) {
        return profileService.getProfileById(userId);
    }




}
