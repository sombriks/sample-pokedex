-- liquibase formatted sql

-- this time we set a context so the changeset only gets applied if the
-- environment is right

-- changeset sombriks:2026-01-25-14-00-insert-test-user-1 context:dev OR test

insert into poke_users(username, password)
-- values ('user', 'pass');
values ('user', '$2a$10$/bMpemnOX/K/21HLOzAg0OEmI8P6Z91CECDUItaBOxVWeKR3CDI4m');

-- rollback delete from poke_users;
