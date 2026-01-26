package com.backend.v1.model;

import com.backend.v1.common.entity.MasterData;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates extends MasterData {

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @OneToOne(mappedBy = "coordinates", cascade = CascadeType.ALL)
    private Address address;

    public Coordinates(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }
}
