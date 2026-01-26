package com.backend.v1.service.serviceImpl;

import com.backend.v1.common.response.ApiResponse;
import com.backend.v1.dto.request.AddressRequestDto;
import com.backend.v1.dto.request.CoordinatesRequestDto;
import com.backend.v1.dto.response.CoordinatesResponseDto;
import com.backend.v1.model.Coordinates;
import com.backend.v1.repository.CoordinateRepository;
import com.backend.v1.service.CoordinatesService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CoordinatesServiceImpl implements CoordinatesService {

    private final CoordinateRepository coordinateRepository;

    @Override
    public List<CoordinatesRequestDto> getAllCoordinates() {
//        Coordinates coordinates1=new Coordinates();
        var coordinates = coordinateRepository.findAll();
        List<CoordinatesRequestDto> requestDtos = new ArrayList<>();
        for(var co: coordinates) {
            CoordinatesRequestDto dto = new CoordinatesRequestDto();

            dto.setLatitude(co.getLatitude());
            dto.setLongitude(co.getLongitude());

            AddressRequestDto address = new AddressRequestDto();


            address.setCity(co.getAddress().getCity());
            address.setRoad(co.getAddress().getRoad());
            address.setStreet(co.getAddress().getStreet());
            address.setTownship(co.getAddress().getCity());

            dto.setAddressRequestDto(address);

            requestDtos.add(dto);

        }
        return requestDtos;

    }




}

