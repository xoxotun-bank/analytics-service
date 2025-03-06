-- liquibase formatted sql
-- changeset Terekhin Bogdan:a0014

update selected_products
set financial_product_id = (select financial_products.id
                            from financial_products limit 1)
where financial_product_id is null;

alter table selected_products
    alter column financial_product_id set not null;
