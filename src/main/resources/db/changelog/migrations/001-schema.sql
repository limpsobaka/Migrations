--liquibase formatted sql

--changeset smirnov:1
create table customers
(
    id           serial primary key,
    "name"       varchar(255),
    surname      varchar(255),
    "age"        int,
    phone_number bigint
);
--rollback drop table customers
--changeset smirnov:2
create table orders
(
    id           serial primary key,
    "date"       timestamp,
    customer_id  bigint references customers (id),
    product_name varchar(255),
    amount       int
)
--rollback drop table orders