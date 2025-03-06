--liquibase formatted sql
--changeset Sergey Lvov:a0004
create table capitalization_periods
(
    id    bigserial primary key,
    value varchar(255) not null,
    constraint unique_capitalization_period unique (value)
)