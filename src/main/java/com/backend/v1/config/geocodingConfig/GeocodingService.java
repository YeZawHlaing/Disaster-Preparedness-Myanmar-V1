package com.backend.v1.config.geocodingConfig;

import com.backend.v1.model.Coordinates;

public interface GeocodingService {

    public Coordinates getCoordinatesFromLocation(String location);
}