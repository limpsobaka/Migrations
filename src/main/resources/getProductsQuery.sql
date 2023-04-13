select o.product_name as "Название продукта"
from orders o
join customers c on c.id = o.customer_id
where LOWER(c."name") = LOWER(:name)