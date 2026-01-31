package com.backend.v1.controller;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.request.RegionRequestDto;
import com.backend.v1.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @PostMapping
    public ApiResponse create(
            @RequestBody RegionRequestDto dto) {
        return regionService.create(dto);
    }

    @GetMapping
    public ApiResponse getAll() {
        return regionService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(
            @PathVariable Long id) {
        return regionService.getById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse update(
            @PathVariable Long id,
            @RequestBody RegionRequestDto dto) {
        return regionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return regionService.delete(id);
    }
}

