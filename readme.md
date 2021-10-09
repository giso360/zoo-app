# ZOO APP

This is a dockerized Spring boot CRUD application using PostgreSQL RDBMS. No unit tests are included. 


## DESCRIPTION

The service will basically act as an inventory of animals that are available in our Virtual Zoo. Our visitors will be able to browse all available animals and also, we will give them the ability to see them perform their tricks!



## HOW TO RUN

1. Download repo

2. Run docker command: 

  `docker run -p 5432:5432 -d -e POSTGRES_USER=zoousr -e POSTGRES_PASSWORD=zoopwd -e POSTGRES_DB=zoodb postgres:11`

3. Package the app by running: `mvn clean package`

4. Copy produced .Jar file from **target** folder to the folder named **docker**

5. Stop and remove previous Postgres container and associated volumes

6. Navigate inside docker folder and in there issue command: `docker-compose up`

7. Connect to DB of container using psql OR an SQL client of your choosing.



## API

**Note**: All requests are **GET**

- Get all animals: `localhost:8082/myZoo/animals/all`
- Get all animals by species (e.g. cat, dog - case insensitive): `localhost:8082/myZoo/animals/all/{**speciesName**}`
- Make animal perform trick by choosing its UUID: `localhost:8082/myZoo/animals/{**UUID**}/doTrick`
- Make animal learn trick by choosing its UUID: `localhost:8082/myZoo/animals/{**UUID**}}/learnTrick`