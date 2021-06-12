# Welcome to Easy Inventory!

## Description

<h4><a href="https://spring.io/">Spring boot framework</a></h4>
## Setup
<h4>Environment setup:</h4>
- Setup Java SDK: Download Java SDK <a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">here</a>
- Setup maven: follow step in the <a href="https://maven.apache.org/install.html">docs</a> (Remember follow setep setup in README.txt)
- Required setup:
    - <a href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">Java JDK</a>: 15.0.1
    - <a href="https://maven.apache.org/install.html">Apache Maven</a>: 3.6.3
    - <a href="https://www.enterprisedb.com/downloads/postgres-postgresql-downloads">Postgresql</a>: 10.14
- Development:
    - <a href="EnvFile - plugin intellij">EnvFile</a>- plugin intellij
    
<h4>Environment variables setup:</h4>
```bash
$ cd mgm-inventory && cp .env.example ./.env
```

- And go to the file then set variable, for example:
```
JWT_SECRET=thisissecretkey
JWT_EXPIRE=604800000
DB_SHOW_SQL=true
DB_URL=jdbc:postgresql://localhost:5432/admin-inventory
DB_USERNAME=postgres
DB_PWD=241299
```

<h4>Flyway setup:</h4>
- Before running project we have to export variables for flyway config:
```
set JWT_SECRET=thisissecretkey
set JWT_EXPIRE=604800000
set DB_SHOW_SQL=true
set DB_URL=jdbc:postgresql://localhost:5432/admin-inventory
set DB_USERNAME=postgres
set DB_PWD=241299
```
- Run migration with script:  
```bash
$ mvn -Dflyway.configFiles=flywayConfig.properties flyway:migrate
```
- Rollback migration with script:
```bash
$ mvn -Dflyway.configFiles=flywayConfig.properties flyway:clean
```

## Run project
- After setup everything for development you just need to run:
```bash
$ mvn spring-boot:run
```

- If you want to run in docker environment:
```bash
$ docker build -t mgm/easy-inventory-be:0.0.1 .
$ docker run --env-file .env -p 8080:8080 mgm/easy-inventory-be:0.0.1
```

- If you run docker with your local postgresql you should config your url like this:

```bash
DB_URL=jdbc:postgresql://host.docker.internal:5432/admin-inventory
```

## API Documentation
```bash
http://localhost:8080/swagger-ui.html
```
