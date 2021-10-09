package com.virtualzoo.zoo.util;

import com.virtualzoo.zoo.entities.Animal;
import com.virtualzoo.zoo.model.AnimalDto;

import java.util.ArrayList;
import java.util.List;

public class AnimalConverter {

    public static AnimalDto animalConverter(Animal animal){
        AnimalDto animalDto = new AnimalDto();
        List<String> tricks = new ArrayList<>();
        animalDto.setAnimalIdentity(animal.getAnimalId().toString());
        animalDto.setSpeciesName(animal.getAnimalSpecies().getSpeciesName());
        animalDto.setAnimalName(animal.getAnimalName());
        animal.getTrickSet().forEach(trick -> tricks.add(trick.getTrickName()));
        animalDto.setTrickList(tricks);
        return animalDto;
    }

}
