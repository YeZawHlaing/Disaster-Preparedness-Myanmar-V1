package com.backend.v1.service.serviceImpl;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.request.RegionRequestDto;
import com.backend.v1.dto.response.RegionResponseDto;
import com.backend.v1.exceptions.RegionNotFoundException;
import com.backend.v1.model.Region;
import com.backend.v1.repository.RegionRepository;
import com.backend.v1.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public ApiResponse create(RegionRequestDto dto) {
        Region region = new Region();
        region.setName(dto.getName());

        regionRepository.save(region);

        return ApiResponse.builder()
                .code(200)
                .message("Region created successfully")
                .data(new RegionResponseDto(region.getId(), region.getName()))
                .build();
    }

    @Override
    public ApiResponse getAll() {
        List<RegionResponseDto> data = regionRepository.findAll()
                .stream()
                .map(r -> new RegionResponseDto(r.getId(), r.getName()))
                .toList();

        return ApiResponse.builder()
                .code(200)
                .message("Regions fetched")
                .data(data)
                .build();
    }

    @Override
    public ApiResponse getById(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RegionNotFoundException("Region not found"));

        return ApiResponse.builder()
                .code(200)
                .message("Region fetched")
                .data(new RegionResponseDto(region.getId(), region.getName()))
                .build();
    }

    @Override
    public ApiResponse update(Long id, RegionRequestDto dto) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RegionNotFoundException("Region not found"));

        region.setName(dto.getName());
        regionRepository.save(region);

        return ApiResponse.builder()
                .code(200)
                .message("Region updated")
                .data(new RegionResponseDto(region.getId(), region.getName()))
                .build();
    }

    @Override
    public ApiResponse delete(Long id) {
        regionRepository.deleteById(id);

        return ApiResponse.builder()
                .code(200)
                .message("Region deleted")
                .data(null)
                .build();
    }
}
