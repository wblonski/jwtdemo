Wersja developerska - prace trwają. 
Ponieważ mój kod zasadniczo został w firmach, gdzie pracowałem, jest to rekonstrukcja z pamięci. Mogą być błędy. 
Z grubsza jest tak: 
* Aplikacja buduje się bez warningów, pom.xml raczej poprawny.
* Wdraża się poprwnie bez problemów na TomcatEE lokalnie, gdzie jest poprawnie zainicjowana baza postgres.
* Nie wdraża sie na dockerze, bo trzeba dopiąć skrypt SQL inicjujący bazę: tabela User itd. 
* SecurityConfiguration nie jest skonfigurowane, na ten moment.

Inspirowałem się tym: https://www.youtube.com/watch?v=6r-MpAWVw6c 

Skrypt roboczo(!) wygląda jakoś tak:

create table "user"
(
    id          integer not null
        primary key,
    mfa_enabled boolean not null,
    email       varchar(255),
    firstname   varchar(255),
    lastname    varchar(255),
    password    varchar(255),
    role        varchar(255)
        constraint user_role_check
            check ((role)::text = ANY ((ARRAY ['ADMIN'::character varying, 'MANAGER'::character varying])::text[])),
    secret      varchar(255)
);

alter table "user"
    owner to master;

insert into public.user (id, mfa_enabled, email, firstname, lastname, password, role, secret)
VALUES (1, true, 'jan.kowalski@domain.com', 'Jan', 'Kowalski',  'password', 'ADMIN', '404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970');
