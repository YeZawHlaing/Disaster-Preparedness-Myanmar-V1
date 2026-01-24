package com.backend.v1.service.serviceImpl;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.common.storage.StorageService;
import com.backend.v1.common.storage.StorageServiceFactory;
import com.backend.v1.config.geocodingConfig.GeocodingService;
import com.backend.v1.dto.request.ProfileRequestDto;
import com.backend.v1.dto.response.ProfileResponseDto;
import com.backend.v1.model.*;
import com.backend.v1.repository.*;
import com.backend.v1.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final RoleRepository roleRepository;
    private final CoordinateRepository coordinateRepository;
    private final RegionRepository regionRepository;
    private final LocationRepository locationRepository;


    private final ModelMapper modelMapper;
    private StorageService storageService;
    private final GeocodingService geocodingService;


    @Autowired
    public void setStorageService(StorageServiceFactory factory) {
        this.storageService = factory.getConfiguredStorageService();
    }

    @Transactional
    public String uploadProfilePicture(final Long userId, final MultipartFile file) {
        final Profile profile = this.profileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user ID: " + userId));


        if (file != null && !file.isEmpty()) {
            String filename = storageService.store(file);
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/")
                    .path(filename)
                    .toUriString();
            profile.setProfilePic(fileUrl);
            this.profileRepository.save(profile);
            return fileUrl;

        } else {
            return profile.getProfilePic();
        }

    }


    //start

    @Override
    public ApiResponse createProfile(Long userId, ProfileRequestDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Address address = new Address();
        address.setCity(dto.getAddress().getCity());
        address.setTownship(dto.getAddress().getTownship());
        address.setRoad(dto.getAddress().getRoad());
        address.setStreet(dto.getAddress().getStreet());


        String fullAddress = String.join(", ",
                address.getStreet(),
                address.getRoad(),
                address.getTownship(),
                address.getCity()
        );

        Coordinates coordinates = geocodingService.getCoordinatesFromLocation(fullAddress);

        if (coordinates != null) {
            coordinates.setAddress(address);
            address.setCoordinates(coordinates);
        }


        Region region = regionRepository.findByName(dto.getRegion().getName())
                .orElseThrow(() -> new RuntimeException("Region not found"));


        Profile profile = new Profile();
        profile.setUser(user);
        profile.setFullName(dto.getFullName());
        profile.setContact(dto.getContact());   // 🔥 THIS WAS MISSING
        profile.setNrc(dto.getNrc());
        profile.setDob(dto.getDob());
        profile.setGender(dto.getGender());
        profile.setSocialUrl(dto.getSocialUrl());
        profile.setAddress(address);
        profile.setRegion(region);

        profileRepository.save(profile);

        return new ApiResponse("Profile created successfully");
    }


    //end

    @Override
    public ApiResponse softDeleteProfile(Long userId) {
        return null;
    }

    @Override
    public ApiResponse updateProfile(Long userId, ProfileRequestDto profileRequest) {
        return null;
    }

    @Override
    public ApiResponse getProfileById(final Long userId) {

        // ✅ Ensure user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // ✅ Fetch profile by userId
        Profile profile = profileRepository.findByUser_Id(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        // ✅ Map to response DTO
        ProfileResponseDto responseDto = modelMapper.map(profile, ProfileResponseDto.class);

        return ApiResponse.builder()
                .success(1)
                .code(200)
                .data(responseDto)
                .message("Profile fetched successfully")
                .build();
    }

}
