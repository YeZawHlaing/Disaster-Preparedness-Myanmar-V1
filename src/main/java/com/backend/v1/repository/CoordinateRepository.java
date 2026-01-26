package com.backend.v1.repository;

import com.backend.v1.model.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinates,Long> {

}
