# International Phone Number Validator

This project consists of backend application which is the spring boot application that loads sqlite 3 database by using spring data and also consisting of frontend application that was developed using anulgar 12 framework.


## How to run project

- By running the docker-coompse found in the project where it build and runs the backend spring boot on port 8090 & frontend angular on port 4200
```
docker-compose up -d
```

Note: the backend dockerfile has two stages; 
- mvn clean package to clean and build the project into jar (which might takes couple of minutes the first run as it downloads the original images)
- To run the jar.

- Each project has dockerfile that is used to build the image.
