package com.backend.v1.model;


import com.backend.v1.common.entity.MasterData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends MasterData {

    @Column(unique = true)
    private String name;

}
