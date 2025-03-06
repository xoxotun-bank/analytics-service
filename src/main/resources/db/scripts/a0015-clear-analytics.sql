-- liquibase formatted sql
-- changeset Sergey Lvov:a0015

truncate selected_products restart identity cascade;
truncate financial_products restart identity cascade;