-- liquibase formatted sql
-- changeset Sergey Lvov:a0011

alter table selected_products
    add column client_age integer,
    add column is_new_client boolean,
    add column user_id bigint default -1 not null,
    add column city varchar(255) default '' not null,
    add column region varchar(255) default '' not null,
    add column is_successfully_selected  boolean default true not null;

alter table selected_products
    alter column  user_id drop default,
    alter column  city drop default,
    alter column  region drop default,
    alter column  is_successfully_selected drop default;

alter table selected_products
    alter column financial_product_id drop not null;
