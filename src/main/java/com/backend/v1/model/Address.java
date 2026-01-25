package com.backend.v1.model;

import com.backend.v1.common.entity.MasterData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
    private Coordinates coordinates;

}
