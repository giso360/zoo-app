package com.virtualzoo.zoo.repositories;

import com.virtualzoo.zoo.entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
}
