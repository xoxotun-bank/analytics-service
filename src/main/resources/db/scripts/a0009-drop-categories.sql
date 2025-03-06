--liquibase formatted sql
--changeset Sergey Lvov:a0009

alter table financial_products
    add column category              varchar(255) not null,
    drop constraint financial_products_category_id_fkey;

create index financial_products_category_idx
    on financial_products(category);

update financial_products
set category = (
    select cat.name
    from categories as cat
    where cat.id = financial_products.category_id
);


alter table financial_products
    drop column category_id;

drop table categories;
