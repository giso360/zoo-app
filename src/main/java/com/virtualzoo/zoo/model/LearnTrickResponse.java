package com.virtualzoo.zoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearnTrickResponse {

    private String message;
    private String animalId;
    private String animalName;
    private List<String> oldTricks;
    private List<String> newTricks;
    private String newTrickLearned;

}
