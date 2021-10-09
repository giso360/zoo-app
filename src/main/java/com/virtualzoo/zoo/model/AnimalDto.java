package com.virtualzoo.zoo.model;

import lombok.Data;

import java.util.List;

@Data
public class AnimalDto {

    private String animalIdentity;

    private String animalName;

    private String speciesName;

    private List<String> trickList;

}
