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

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "`longitude`", precision = 11, scale = 8)
    private BigDecimal longitude;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "location_id")
    private Location location;

}
