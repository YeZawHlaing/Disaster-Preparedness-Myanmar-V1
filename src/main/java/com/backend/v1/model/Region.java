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
public class Region extends MasterData {

    @Column
    private String region_name;

    @OneToOne(mappedBy = "region", cascade = CascadeType.ALL)
    private Profile profile;

}
