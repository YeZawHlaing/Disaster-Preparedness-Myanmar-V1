package com.backend.v1.service.serviceImpl;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.request.CoordinatesRequestDto;
import com.backend.v1.dto.response.CoordinatesResponseDto;
import com.backend.v1.model.Coordinates;
import com.backend.v1.repository.CoordinateRepository;
import com.backend.v1.service.CoordinatesService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class CoordinatesServiceImpl implements CoordinatesService {

    private final CoordinateRepository coordinateRepository;

    @Override
    public ApiResponse getAllCoordinates() {
//        Coordinates coordinates1=new Coordinates();
        var coordinates = coordinateRepository.findAll();
        List<CoordinatesRequestDto> resquestDtos = new ArrayList<>();
        for(var co: coordinates) {
            CoordinatesRequestDto dto = new CoordinatesRequestDto();

            dto.setLatitude(co.getLatitude());
            dto.setLongitude(co.getLongitude());
            resquestDtos.add(dto);
//            responseDtos.add(dto);
        }
        return ApiResponse.builder().code(HttpStatus.SC_OK).data(resquestDtos).message("Fetched Coordinates").build();
    }




}

