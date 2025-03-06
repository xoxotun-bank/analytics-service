--liquibase formatted sql
--changeset Sergey Lvov:a0007

alter table financial_products
    add column period integer not null,
    drop constraint financial_products_period_id_fkey;

create index financial_products_period_idx
    on financial_products (period);

update financial_products
set period = (
    select per.value
    from periods as per
    where per.id = financial_products.period_id
);

alter table financial_products
    drop column period_id;

drop table periods;
