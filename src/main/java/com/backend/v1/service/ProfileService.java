package com.backend.v1.service;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.request.ProfileRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {

    public String uploadProfilePicture(final Long userId, final MultipartFile file);

//    public ApiResponse createProfile(final Long userId, final ProfileRequestDto profileRequest, final MultipartFile file);
    public ApiResponse createProfile(final Long userId, final ProfileRequestDto profileRequest);

    public ApiResponse softDeleteProfile(final Long userId);

    public ApiResponse updateProfile(final Long userId,final ProfileRequestDto profileRequest);

    public ApiResponse getProfileById(final Long userId);



}
