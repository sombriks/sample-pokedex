-- liquibase formatted sql

-- changeset sombriks:2026-01-25-12-00-create-poke-user-1

create table poke_users
(
    id       serial       not null primary key,
    username varchar(255) not null unique,
    password varchar(255) not null,
    created  timestamp    not null default current_timestamp
);

-- rollback drop table poke_users;
