package com.virtualzoo.zoo.service;

import com.virtualzoo.zoo.model.AnimalDto;
import com.virtualzoo.zoo.model.LearnTrickResponse;
import com.virtualzoo.zoo.model.TrickDto;

import java.util.List;
import java.util.UUID;

public interface AnimalService {

    List<AnimalDto> getAllAnimalsOverview();

    List<AnimalDto> getAllAnimalsBySpeciesOverview(String speciesName);

    TrickDto performTrick(UUID animalId);

    LearnTrickResponse learnNewTrick(UUID animalId);

}
