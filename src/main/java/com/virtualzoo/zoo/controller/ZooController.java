package com.virtualzoo.zoo.controller;

import com.virtualzoo.zoo.exception.AnimalNotFoundException;
import com.virtualzoo.zoo.exception.NoNewTricksToBeLearnedException;
import com.virtualzoo.zoo.exception.SpeciesNotFoundException;
import com.virtualzoo.zoo.model.AnimalDto;
import com.virtualzoo.zoo.model.LearnTrickResponse;
import com.virtualzoo.zoo.model.TrickDto;
import com.virtualzoo.zoo.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("myZoo/animals")
@Slf4j
public class ZooController {

    private final AnimalService animalService;

    public ZooController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnimalDto>> getAllAnimals() {
        try {
            return ResponseEntity.ok(animalService.getAllAnimalsOverview());
        } catch (Exception e) {
            log.error("Could not retrieve animals: {}", e.getMessage(), e);
            throw e;
        }
    }


    @GetMapping("all/{speciesName}")
    public ResponseEntity<List<AnimalDto>> getAllAnimalsBySpecies(@PathVariable String speciesName) {
        try {
            return ResponseEntity.ok(animalService.getAllAnimalsBySpeciesOverview(speciesName));
        } catch (SpeciesNotFoundException speciesExc){
            log.error("Could not retrieve animals of species - {}", speciesName);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        } catch (Exception e) {
            log.error("Could not retrieve animals: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @GetMapping("{animalIdIn}/doTrick")
    public ResponseEntity<TrickDto> performTrick(@PathVariable String animalIdIn){
        UUID animalId;
        try {
            animalId = UUID.fromString(animalIdIn);
        } catch (IllegalArgumentException e){
            log.error("Animal ID is not valid UUID");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new TrickDto());
        }
        try {
            return ResponseEntity.ok(animalService.performTrick(animalId));
        } catch (AnimalNotFoundException ex) {
            log.error("Could not retrieve animal with UUID - {}", animalId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TrickDto());
        } catch (Exception e) {
            log.error("Could not retrieve animals: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TrickDto());
        }
    }

    @GetMapping("{animalIdIn}/learnTrick")
    public ResponseEntity<LearnTrickResponse> learnNewTrick(@PathVariable String animalIdIn) {
        UUID animalId;
        LearnTrickResponse errorResponse = new LearnTrickResponse();
        try {
            animalId = UUID.fromString(animalIdIn);
        } catch (IllegalArgumentException e){
            log.error("Animal ID is not valid UUID");
            errorResponse.setMessage("ANIMAL ID PROVIDED IS NOT ACCEPTABLE UUID");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
        }
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(animalService.learnNewTrick(animalId));
        } catch (AnimalNotFoundException ex1) {
            log.error("Could not retrieve animal with UUID - {}", animalId);
            errorResponse.setMessage("ANIMAL WITH UUID: " + animalId + " - NOT FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (NoNewTricksToBeLearnedException ex2) {
            log.error("Could not learn more tricks to animal with ID: {}", animalId);
            errorResponse.setMessage("NO NEW TRICK LEARNED");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
        }
    }



}
