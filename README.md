**Shows a basic backend application running with a PostGres Docker Container pulled from dockerhub** 

Run this in the command line. Make sure you have Docker Desktop downloaded (for Windows users). 

$ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres:15-alpine

**Make sure the username and password are the same as specified in your application.properties file **

* Open bash to add a new database to the container that is running *
$docker exec -it container_number bin/bash

* Run this command to execute sql commands * 
$psql -U postgres

* Create a database *
$CREATE DATABASE demodb;

* Check that the database is there
$\l

You can now start your Spring application and perform GET, POST, PUT, DELETE requests using your IDE or tools like Postman. :)
