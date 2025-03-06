--liquibase formatted sql
--changeset Sergey Lvov:a0005
create table financial_products
(
    id                             bigserial primary key,
    name                           varchar(255) not null,
    period_id                      bigint       not null,
    category_id                    bigint       not null,
    can_deposit                    boolean      not null,
    can_withdrawal                 boolean      not null,
    capitalization_to_same_account boolean      not null,
    percent                        decimal      not null,
    capitalization_period_id       bigint       not null,
    currency_id                    bigint       not null,

    foreign key (capitalization_period_id) references capitalization_periods,
    foreign key (currency_id) references currencies,
    foreign key (category_id) references categories,
    foreign key (period_id) references periods
)