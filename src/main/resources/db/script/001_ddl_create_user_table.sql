create table users (
    id serial primary key not null,
    login varchar(7) unique,
    password varchar(256),
    site varchar(128) unique
);