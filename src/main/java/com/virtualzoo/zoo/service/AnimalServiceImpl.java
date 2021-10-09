package com.virtualzoo.zoo.service;

import static com.virtualzoo.zoo.util.AnimalConverter.animalConverter;
import static com.virtualzoo.zoo.util.RandomSelector.getRandomStringFromList;

import com.virtualzoo.zoo.entities.Animal;
import com.virtualzoo.zoo.entities.Species;
import com.virtualzoo.zoo.exception.AnimalNotFoundException;
import com.virtualzoo.zoo.exception.NoNewTricksToBeLearnedException;
import com.virtualzoo.zoo.exception.SpeciesNotFoundException;
import com.virtualzoo.zoo.model.AnimalDto;
import com.virtualzoo.zoo.model.LearnTrickResponse;
import com.virtualzoo.zoo.model.SpeciesOverview;
import com.virtualzoo.zoo.model.TrickDto;
import com.virtualzoo.zoo.repositories.AnimalRepository;
import com.virtualzoo.zoo.repositories.SpeciesRepository;
import com.virtualzoo.zoo.repositories.TrickRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@Service
@Slf4j
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    private final SpeciesRepository speciesRepository;

    private final TrickRepository trickRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository,
                             SpeciesRepository speciesRepository,
                             TrickRepository trickRepository) {
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
        this.trickRepository = trickRepository;
    }

    @Override
    public List<AnimalDto> getAllAnimalsOverview() {
        List<AnimalDto> animalDtos = new ArrayList<>();
        animalRepository.findAll().forEach(animal -> animalDtos.add(animalConverter(animal)));
        return animalDtos;
    }

    @Override
    public List<AnimalDto> getAllAnimalsBySpeciesOverview(String speciesName) {

        SpeciesOverview speciesOverview = new SpeciesOverview(speciesRepository.findAll());

        List<String> allSpeciesNames = new ArrayList<>();
        speciesOverview.getSpeciesList().forEach(species -> allSpeciesNames.add(species.getSpeciesName()));

        // If speciesName not in List -> throw SpeciesNotFoundException
        if (!isSpeciesPresent(allSpeciesNames, speciesName)) {
            throw new SpeciesNotFoundException("Species named: " + speciesName + " NOT FOUND");
        }

        // else find speciesId corresponding to speciesName & execute AnimalRepository action
        Species targetSpecies = speciesOverview.getSpeciesList().stream()
            .filter(species -> species.getSpeciesName().equalsIgnoreCase(speciesName)).collect(
                Collectors.toList()).get(0);

        List<AnimalDto> response = new ArrayList<>();

        animalRepository.findAnimalsByAnimalSpecies(targetSpecies)
            .forEach(animal -> response.add(animalConverter(animal))
            );

        return response;
    }

    @Override
    public TrickDto performTrick(UUID animalId) {
        // get animal by animalId
        Optional<Animal> animalOptional = animalRepository.findAnimalByAnimalId(animalId);

        // if not exist throw new AnimalNotFoundException
        if (animalOptional.isEmpty()) {
            throw new AnimalNotFoundException("Animal having ID: " + animalId + " NOT FOUND");
        }

        // else select all available tricks for animal & and choose one randomly
        List<String> trickNames = new ArrayList<>();
        animalOptional.get().getTrickSet().forEach(trick -> trickNames.add(trick.getTrickName()));

        return new TrickDto(getRandomStringFromList(trickNames));
    }

    @Override
    @Transactional
    public LearnTrickResponse learnNewTrick(UUID animalId) {
        LearnTrickResponse learnTrickResponse = new LearnTrickResponse();

        // Check if animal exists
        if (animalRepository.findAnimalByAnimalId(animalId).isEmpty()){
            throw new AnimalNotFoundException("Animal having ID: " + animalId + " NOT FOUND");
        }

        // get tricks for species
        List<String> tricksForSpecies = new ArrayList<>();
        animalRepository.getTricksForSpeciesByAnimalId(animalId).get()
            .forEach(tricksForSpeciesById -> tricksForSpecies.add(tricksForSpeciesById.getTricksForSpecies()));

        // get tricks for animal
        List<String> tricksForAnimal = new ArrayList<>();
        animalRepository.getTricksForAnimalByAnimalId(animalId).get()
            .forEach(tricksForAnimalById -> tricksForAnimal.add(tricksForAnimalById.getTricksForAnimal()));

        // calculate difference of 2 lists and pick first element IF difference is more than zero tricks
        List<String> trickDifference = new ArrayList<>(tricksForSpecies);
        trickDifference.removeIf(tricksForAnimal::contains);
        if (trickDifference.size() == 0) {
            throw new NoNewTricksToBeLearnedException("No new tricks available for animal with ID: " + animalId);
        }
        String newTrick = trickDifference.get(0);
        log.info(newTrick);

        // find trickID for newTrick
        log.info("========");
        Long trickId = trickRepository.findByTrickName(newTrick).getTrickId();
        log.info(trickId.toString());
        log.info("========");
        log.info(animalId.toString());

        log.info("========");
        learnTrickResponse.setAnimalId(animalId.toString());
        learnTrickResponse.setAnimalName(animalRepository.findById(animalId).get().getAnimalName());
        learnTrickResponse.setNewTrickLearned(newTrick);
        learnTrickResponse.setMessage("NEW TRICK LEARNED");
        learnTrickResponse.setOldTricks(tricksForAnimal);
        List<String> newTricks = new ArrayList<>(tricksForAnimal);
        newTricks.add(newTrick);
        learnTrickResponse.setNewTricks(newTricks);

        log.info(learnTrickResponse.toString());

        // save new record in animal_tricks with animalId, trickID
        animalRepository.learnNewTrickQuery(animalId, trickId);

        return learnTrickResponse;
    }

    private boolean isSpeciesPresent(List<String> allSpeciesNames, String speciesName) {
        return allSpeciesNames.contains(speciesName.toLowerCase());
    }


}
