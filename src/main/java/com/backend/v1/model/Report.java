package com.backend.v1.model;

import com.backend.v1.common.constant.ReportStatus;
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
public class Report extends MasterData {

    @Column
    private String description;

    @Column(unique = true, nullable = false)
    private String contact;

    @Column
    private String pic1;

    @Column
    private String pic2;

    @Column
    private String pic3;

    @Column
    private String report_type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus reportStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
