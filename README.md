# Spring Boot 3.5.5 Security with JWT Impementation
  Prosta implementacja JWT Authentication z użyciem SpringBoot 3.5.5, oraz JSON Web Token (JWT). 
  Nie jest to implementacja kompletna, gotowa do wdrożenia na produkcję, a tylko szkielet. 
  Inspirowałem się tym: https://www.youtube.com/watch?v=6r-MpAWVw6c 

## Funkcjonalności
Tylko kilka najprostszych:
* rejestracja nowego użytkownika:
      POST http://localhost:8080/api/v1/auth/register
      Content-Type: application/json
      {
        "firstname": "jan",
        "lastname": "kowalski",
        "email": "jan.kowalski@domain.com",
        "password": "password",
        "role": "ADMIN",
        "mfaEnabled": false
      }
* autentykacja zarejestrowanego użytkownika, 
      POST http://localhost:8080/api/v1/auth/authenticate
      Content-Type: application/json
      {
        "email": "jan.kowalski@domain.com",
        "password": "password"
      }
* odświeżenie tokena
      POST http://localhost:8080/api/v1/auth/refresh-token
      Content-Type: application/x-www-form-urlencoded

* weryfikacja tokena
      POST http://localhost:8080/api/v1/auth/verify
      Content-Type: application/json
      {
      "email": "jan.kowalski@domain.com",
      "code": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW4ua293YWxza2lAZG9tYWluLmNvbSIsImlhdCI6MTc1Njk2MzMyMywiZXhwIjoxNzU3NTY4MTIzfQ.MQCDfuq58QvLKdSmdjACixvg5QHjuGvbBAV1o12d6KY"
      }
## Technologie
* Spring Boot 3.5.5
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven 3+
* Postgres
* Docker
* JDK 17+

## Aby rozpocząć
* Utwórz kontener z bazą Postgres - standardowa konfiguracja, i utwórz tabelę jwt_user w schemacie "public" skryptem:
      create table jwt_user
      (
          id          integer not null primary key,
          mfa_enabled boolean not null,
          email       varchar(255),
          firstname   varchar(255),
          lastname    varchar(255),
          password    varchar(255),
          role        varchar(255)
              constraint jwt_user_role_check
                  check ((role)::text = ANY
                         ((ARRAY ['ADMIN'::character varying, 'MANAGER'::character varying, 'USER'::character varying, 'GUEST'::character varying])::text[])),
          secret      varchar(255)
      );
      alter table jwt_user
          owner to master;
      -- master = aktualny użytkownik systemu
* Zbuduj jar mavenem
* Uruchom
  pod IDE lub: java -jar jwtdemo-1.0.0-SNAPSHOT.jar
* Aplikacja dostępna na localhost:8080


  
