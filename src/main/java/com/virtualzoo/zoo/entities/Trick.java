package com.virtualzoo.zoo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tricks", schema = "public")
public class Trick {

    @Id
    @Column(name = "trick_id")
    private Long trickId;

    @Column(name = "trick_name")
    private String trickName;

    @ManyToMany
    @JsonIgnore
    private Set<Animal> animals = new HashSet<>();

}
