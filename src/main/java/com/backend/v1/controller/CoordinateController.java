package com.backend.v1.controller;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.response.CoordinatesResponseDto;
import com.backend.v1.service.CoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")

public class CoordinateController {

    private final CoordinatesService coordinatesService;
    @GetMapping("/coordinates")
    public ApiResponse getAllCoordinates() {
        var coordinates = coordinatesService.getAllCoordinates();
        return ApiResponse.builder().code(HttpStatus.OK.value()).message("ALL COORDNIATES FETCHED").data(coordinates).build();
    }




}
