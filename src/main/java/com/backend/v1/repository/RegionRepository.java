package com.backend.v1.repository;

import com.backend.v1.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
public interface RegionRepository extends JpaRepository<Region,Long> {

    Optional<Region> findByName(String name);

    boolean existsByName(String name);
}
