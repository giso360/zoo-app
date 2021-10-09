package com.virtualzoo.zoo.model;

import com.virtualzoo.zoo.entities.Species;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesOverview {

    private List<Species> speciesList;

}
