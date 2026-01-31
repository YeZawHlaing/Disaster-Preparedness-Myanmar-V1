package com.backend.v1.model;

import com.backend.v1.common.entity.MasterData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organization extends MasterData {

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String SOS;

    @Column
    private String profile_pic;

    @Column
    private String social_url;

    @Column(nullable = false)
    private LocalDate founded_year;

    @Column
    private String description;

    @Column(nullable = false)
    private String service_type;

    @Column
    private String mission_statement;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(
            mappedBy = "organization",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Achievement> achievements = new ArrayList<>();




}
