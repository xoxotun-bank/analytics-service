--liquibase formatted sql
--changeset Sergey Lvov:a0008

alter table financial_products
    add column currency varchar(255) not null,
    drop constraint financial_products_currency_id_fkey;

create index financial_products_currency_idx
    on financial_products(currency);

update financial_products
set currency = (
    select cur.currency
    from currencies as cur
    where cur.id = financial_products.currency_id
);

alter table financial_products drop column currency_id;

drop table currencies;
