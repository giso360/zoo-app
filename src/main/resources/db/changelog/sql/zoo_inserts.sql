--------------------------
--- INSERT INTO SPECIES---
--------------------------

INSERT INTO public.species (species_id, species_name) VALUES (1, 'dog');
INSERT INTO public.species (species_id, species_name) VALUES (2, 'cat');

--------------------------
--- INSERT INTO ANIMALS---
--------------------------

INSERT INTO public.animals (animal_id, animal_name, animal_species) VALUES ('476bed0a-2858-11ec-9621-0242ac130002', 'maha', 1);
INSERT INTO public.animals (animal_id, animal_name, animal_species) VALUES ('476be88c-2858-11ec-9621-0242ac130002', 'tara', 1);
INSERT INTO public.animals (animal_id, animal_name, animal_species) VALUES ('476beaa8-2858-11ec-9621-0242ac130002', 'kenzi', 2);

--------------------------
--- INSERT INTO TRICKS ---
--------------------------

INSERT INTO public.tricks (trick_id, trick_name) VALUES (1, 'run');
INSERT INTO public.tricks (trick_id, trick_name) VALUES (2, 'jump');
INSERT INTO public.tricks (trick_id, trick_name) VALUES (3, 'roll over');
INSERT INTO public.tricks (trick_id, trick_name) VALUES (4, 'meaow');
INSERT INTO public.tricks (trick_id, trick_name) VALUES (5, 'bark');

----------------------------------
--- INSERT INTO SPECIES_TRICKS ---
----------------------------------

INSERT INTO public.species_tricks (species_id, trick_id) VALUES (1, 1);
INSERT INTO public.species_tricks (species_id, trick_id) VALUES (2, 1);
INSERT INTO public.species_tricks (species_id, trick_id) VALUES (1, 2);
INSERT INTO public.species_tricks (species_id, trick_id) VALUES (2, 2);
INSERT INTO public.species_tricks (species_id, trick_id) VALUES (1, 3);
INSERT INTO public.species_tricks (species_id, trick_id) VALUES (2, 4);
INSERT INTO public.species_tricks (species_id, trick_id) VALUES (1, 5);

----------------------------------
--- INSERT INTO ANIMALS TRICKS ---
----------------------------------

INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476bed0a-2858-11ec-9621-0242ac130002', 1);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476bed0a-2858-11ec-9621-0242ac130002', 2);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476bed0a-2858-11ec-9621-0242ac130002', 3);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476bed0a-2858-11ec-9621-0242ac130002', 5);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476be88c-2858-11ec-9621-0242ac130002', 1);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476be88c-2858-11ec-9621-0242ac130002', 2);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476be88c-2858-11ec-9621-0242ac130002', 5);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476beaa8-2858-11ec-9621-0242ac130002', 1);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476beaa8-2858-11ec-9621-0242ac130002', 2);
INSERT INTO public.animal_tricks (animal_id, trick_id) VALUES ('476beaa8-2858-11ec-9621-0242ac130002', 4);













