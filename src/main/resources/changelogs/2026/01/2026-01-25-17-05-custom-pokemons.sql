-- liquibase formatted sql

-- changeset sombriks:2026-01-25-17-05-custom-pokemons-1

-- this first changeset creates the auxiliar tables for the pokemon table

create table abilities
(
    id      serial       not null primary key,
    name    varchar(255) not null unique,
    updated timestamp    not null default current_timestamp
);

create table types
(
    id      serial       not null primary key,
    name    varchar(255) not null unique,
    updated timestamp    not null default current_timestamp
);

create table species
(
    id          integer      not null primary key,
    name        varchar(255) not null unique,
    image_url   varchar(255),
    description text,
    updated     timestamp    not null default current_timestamp
);

-- rollback drop table species;
-- rollback drop table types;
-- rollback drop table abilities;

-- changeset sombriks:2026-01-25-17-05-custom-pokemons-2

-- this changeset creates the pokemon table. it can only existis here if
-- associated with a user.

create table pokemons
(
    id            serial       not null primary key,
    species_id    integer      not null references species (id) on delete cascade,
    poke_users_id integer      not null references poke_users (id) on delete cascade,
    name          varchar(255) not null,
    weight        integer,
    details       jsonb,
    updated       timestamp    not null default current_timestamp
);

-- rollback drop table pokemon;

-- changeset sombriks:2026-01-25-17-05-custom-pokemons-3

-- the many-to-many relationships between pokemons, types and abilities

create table pokemons_abilities
(
    pokemons_id  integer not null references pokemons (id) on delete cascade deferrable initially deferred,
    abilities_id integer not null references abilities (id) on delete cascade deferrable initially deferred,
    primary key (pokemons_id, abilities_id)
);

create table pokemons_types
(
    pokemons_id integer not null references pokemons (id) on delete cascade deferrable initially deferred,
    types_id    integer not null references types (id) on delete cascade deferrable initially deferred,
    primary key (pokemons_id, types_id)
);

create table evolutions
(
    species_id  integer not null references species (id) on delete cascade deferrable initially deferred,
    ancestor_id integer not null references species (id) on delete cascade deferrable initially deferred,
    primary key (species_id, ancestor_id)
);

-- rollback drop table evolutions;
-- rollback drop table pokemons_types;
-- rollback drop table pokemons_abilities;

-- changeset sombriks:2026-01-25-17-05-custom-pokemons-4

-- the time-series, longitudinal tables, the aux tables first

create table abilities_log
(
    revision     serial       not null primary key,
    abilities_id integer      not null references abilities (id) on delete cascade,
    name         varchar(255) not null,
    updated      timestamp    not null default current_timestamp
);

create table types_log
(
    revision serial       not null primary key,
    types_id integer      not null references types (id) on delete cascade,
    name     varchar(255) not null,
    updated  timestamp    not null default current_timestamp
);

create table species_log
(
    revision    serial       not null primary key,
    species_id  integer      not null references species (id) on delete cascade,
    name        varchar(255) not null,
    image_url   varchar(255),
    description text,
    updated     timestamp    not null default current_timestamp
);

-- rollback drop table species_log;
-- rollback drop table types_log;
-- rollback drop table abilities_log;

-- changeset sombriks:2026-01-25-17-05-custom-pokemons-5

-- main analytic table

create table pokemons_log
(
    revision      serial       not null primary key,
    pokemons_id   integer      not null references pokemons (id) on delete cascade,
    species_id    integer      not null references species (id) on delete cascade,
    poke_users_id integer      not null references poke_users (id) on delete cascade,
    name          varchar(255) not null,
    weight        integer,
    details       jsonb,
    updated       timestamp    not null default current_timestamp
);

-- rollback drop table pokemons_log;

-- changeset sombriks:2026-01-25-17-05-custom-pokemons-6

-- relations over the time must be preserved too

create table pokemons_abilities_log
(
    pokemons_log_revision  integer not null references pokemons_log (revision) on delete cascade deferrable initially deferred,
    abilities_log_revision integer not null references abilities_log (revision) on delete cascade deferrable initially deferred,
    primary key (pokemons_log_revision, abilities_log_revision)
);

create table pokemons_types_log
(
    pokemons_log_revision integer not null references pokemons_log (revision) on delete cascade deferrable initially deferred,
    types_log_revision    integer not null references types_log (revision) on delete cascade deferrable initially deferred,
    primary key (pokemons_log_revision, types_log_revision)
);

-- rollback drop table pokemons_types_log;
-- rollback drop table pokemons_abilities_log;
