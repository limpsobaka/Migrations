--liquibase formatted sql

--changeset smirnov:1
insert into customers ("name", surname, "age", phone_number)
values
    ('Федор', 'Федорович', 25, 9012345678),
    ('Иван', 'Иванов', 30, 9084569257),
    ('Павел', 'Павлов', 45, 9992367458),
    ('Сидор', 'Сидоров', 18, 9731648357),
    ('Алексей', 'Алексеев', 32, 9463852478);
insert into orders ("date", customer_id, product_name, amount)
values
    (now(), 1, 'Колбаса', 1),
    (now(), 1, 'Хлеб', 2),
    (now(), 1, 'Молоко', 1),
    (now(), 2, 'Хлеб', 3),
    (now(), 3, 'Колбаса', 2);
--rollback ;