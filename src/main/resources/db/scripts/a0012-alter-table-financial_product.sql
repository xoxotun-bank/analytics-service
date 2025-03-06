-- liquibase formatted sql
-- changeset Sergey Lvov:a0012

alter table financial_products
    alter column id drop default;

drop sequence financial_products_id_seq;
