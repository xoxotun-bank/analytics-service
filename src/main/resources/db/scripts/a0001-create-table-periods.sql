--liquibase formatted sql
--changeset Sergey Lvov:a0001
create table periods
(
    id    bigserial primary key,
    value integer not null,
    constraint unique_period unique (value)
)