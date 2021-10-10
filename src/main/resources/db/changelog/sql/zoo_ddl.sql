----------------------------
-- CREATE TABLE SPECIES
----------------------------

create table if not exists species
(
  species_id serial not null
    constraint species_pk
      primary key,
  species_name varchar(30) not null
);

alter table species owner to zoousr;

create unique index if not exists species_species_id_uindex
  on species (species_id);

----------------------------
-- CREATE TABLE ANIMALS
----------------------------

create table if not exists animals
(
  animal_id uuid not null
    constraint animals_pk
      primary key,
  animal_name varchar(40) not null,
  animal_species integer not null
    constraint animals_species_species_id_fk
      references species
);

alter table animals owner to zoousr;

create unique index if not exists animals_animal_id_uindex
  on animals (animal_id);

----------------------------
-- CREATE TABLE TRICKS
----------------------------

create table if not exists tricks
(
  trick_id serial not null
    constraint tricks_pk
      primary key,
  trick_name varchar(30) not null
);

alter table tricks owner to zoousr;

create unique index if not exists tricks_trick_id_uindex
  on tricks (trick_id);

------------------------------
-- CREATE TABLE SPECIES_TRICKS
------------------------------

create table if not exists species_tricks
(
  species_id integer not null
    constraint species_tricks_species_species_id_fk
      references species,
  trick_id integer not null
    constraint species_tricks_tricks_trick_id_fk
      references tricks
);

alter table species_tricks owner to zoousr;

-----------------------------
-- CREATE TABLE ANIMAL_TRICKS
-----------------------------

create table if not exists animal_tricks
(
  animal_id uuid not null
    constraint animal_tricks_animals_animal_id_fk
      references animals,
  trick_id integer not null
    constraint animal_tricks_tricks_trick_id_fk
      references tricks
);

alter table animal_tricks owner to zoousr;

