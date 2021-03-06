# International Phone Number Validator

- This project consists of backend application which is the spring boot application that loads sqlite 3 database by using spring data and also consisting of frontend application that was developed using anulgar 12 framework.

- The project loads customer numbers into web page where each row has the name of the customer, country, country code, localnumber & also if this number if valid or not due to international phone numbers.

## How to run project

- By running the docker-coompse found in the project where it build and runs the backend spring boot on port 8090 & frontend angular on port 4200
```
docker-compose up -d
```

Note: the backend dockerfile has two stages; 
1. mvn clean package to clean and build the project into jar (which might takes couple of minutes the first run as it downloads the original images)
2. To run the jar.

- Each project has dockerfile that is used to build the image.

## How to run each project seperately

- Backend
```
docker build -t phonenumbervalidator-backend:1.0 .
```

```
docker run -p 8090:8080 -it phonenumbervalidator-backend:1.0
```

- Frontend
```
docker build -t phonenumbervalidator-frontend:1.0 .
```

```
docker run -p 4200:80 -it phonenumbervalidator-frontend:1.0
```