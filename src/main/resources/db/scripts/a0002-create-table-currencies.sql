--liquibase formatted sql
--changeset Sergey Lvov:a0002
create table currencies
(
    id       bigserial primary key,
    currency varchar(255) not null,
    constraint unique_currency unique (currency)
)