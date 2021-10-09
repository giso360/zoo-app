package com.virtualzoo.zoo.repositories;

import com.virtualzoo.zoo.entities.Animal;
import com.virtualzoo.zoo.entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {

    List<Animal> findAnimalsByAnimalSpecies(Species species);

    Optional<Animal> findAnimalByAnimalId(UUID animalId);

    @Query(value = "SELECT trick_name AS tricksForSpecies\n"
        + "FROM species,\n"
        + "     tricks,\n"
        + "     species_tricks\n"
        + "WHERE species.species_id = species_tricks.species_id\n"
        + "  AND tricks.trick_id=species_tricks.trick_id\n"
        + "  AND species.species_id = (\n"
        + "    SELECT animal_species\n"
        + "    FROM animals\n"
        + "    where animal_id = :animalId\n"
        + ")", nativeQuery = true)
    Optional<List<TricksForSpecies>> getTricksForSpeciesByAnimalId(@Param("animalId") UUID animalId);

    @Query(value = "SELECT trick_name AS tricksForAnimal\n"
        + "    FROM animals, tricks, animal_tricks\n"
        + "    WHERE animals.animal_id=animal_tricks.animal_id\n"
        + "    AND tricks.trick_id=animal_tricks.trick_id\n"
        + "    AND animals.animal_id=:animalId", nativeQuery = true)
    Optional<List<TricksForAnimals>> getTricksForAnimalByAnimalId(@Param("animalId") UUID animalId);

    @Modifying
    @Query(value = "INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES (:animalId, :trickId)",
        nativeQuery = true)
    void learnNewTrickQuery(@Param("animalId") UUID animalId, @Param("trickId") Long trickId);


}
