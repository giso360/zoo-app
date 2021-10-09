package com.virtualzoo.zoo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "species")
public class Species {

    @Id
    @Column(name = "species_id")
    private Long speciesId;

    @Column(name = "species_name")
    private String speciesName;

    @OneToMany(mappedBy = "animalSpecies", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Animal> animalSet;

}
