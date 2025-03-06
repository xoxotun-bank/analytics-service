-- liquibase formatted sql
-- changeset Sergey Lvov:a0013

alter table selected_products
    alter column user_id type integer;

alter table selected_products
    alter column region drop not null ;
