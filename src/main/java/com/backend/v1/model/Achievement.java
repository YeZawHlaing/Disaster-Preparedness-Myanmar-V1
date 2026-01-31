package com.backend.v1.model;

import com.backend.v1.common.entity.MasterData;
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
public class Achievement extends MasterData {

    @Column
    private String title;

    @Column
    private String detail;

    @Column
    private String img1;

    @Column
    private String img2;

    @Column
    private String img3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;


}
