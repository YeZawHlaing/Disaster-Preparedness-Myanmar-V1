package com.backend.v1.model;

import com.backend.v1.common.entity.MasterData;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile extends MasterData {

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String contact;

    @Column(nullable = false)
    private LocalDate dob;

    private String socialUrl;

    private String profilePic;

    private String gender;

    @Column(nullable = false, unique = true)
    private String nrc;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

}
