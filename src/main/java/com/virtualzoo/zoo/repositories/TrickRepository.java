package com.virtualzoo.zoo.repositories;

import com.virtualzoo.zoo.entities.Trick;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrickRepository extends JpaRepository<Trick, Long> {

    Trick findByTrickName(String trickName);

}
