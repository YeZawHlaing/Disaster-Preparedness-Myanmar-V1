package com.backend.v1.model;

import com.backend.v1.common.entity.MasterData;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends MasterData {

    @Column
    private String city;

    @Column
    private String township;

    @Column
    private String road;

    @Column
    private String street;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Report report;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Organization organization;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "coordinate_id")
    private Coordinates coordinates;

}
