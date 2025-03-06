--liquibase formatted sql
--changeset Sergey Lvov:a0010

alter table financial_products
    add column capitalization_period varchar(255) not null,
    drop constraint financial_products_capitalization_period_id_fkey;

create index financial_products_capitalization_period_idx
    on financial_products(capitalization_period);

update financial_products
set capitalization_period = (
    select cp.value
    from capitalization_periods as cp
    where cp.id = financial_products.capitalization_period_id
);


alter table financial_products drop column capitalization_period_id;

drop table capitalization_periods;
