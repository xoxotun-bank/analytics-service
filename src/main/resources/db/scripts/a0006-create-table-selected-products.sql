--liquibase formatted sql
--changeset Sergey Lvov:a0006

create table selected_products
(
    id                   bigserial primary key,
    created_at           date not null,
    amount               decimal                  not null,
    financial_product_id bigint                   not null,
    foreign key (financial_product_id) references financial_products
)