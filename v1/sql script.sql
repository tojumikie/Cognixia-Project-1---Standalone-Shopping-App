use shopping_database;
create table customer_accounts (
	account_id integer not null primary key auto_increment,
    username char(30),
    password char(30)
);
create table orders (
	invoice_no varchar(30) not null,
    customer_id integer not null,
    item_code varchar(30),
    quantity integer(4),
    foreign key (customer_id) references customer_accounts(account_id)
 );   
    
create table items(
	item_no integer not null primary key auto_increment,
    item_name varchar(30),
    item_code varchar(30),
    price decimal(7, 2)
);

-- insert into customer_accounts(account_id, username, password) values (3, "testuser", "testpassword");
-- insert into customer_accounts(username, password) values ("testuser", "testpassword");
-- select * from customer_accounts;
delete from customer_accounts;
select * from customer_accounts;

insert into items (item_name, item_code, price) values 
	("jacket", "Ja1", 20.00),
    ("jeans", "Je1", 10.00),
    ("shirt", "Sh1", 5.00);
    
select * from items;
drop table orders; 
select * from orders;
select * from customer_accounts; 

select max(invoice_no) from orders;

select account_id from customer_accounts where username = 'root';

-- (items.price * orders.quantity as total_price)

select * from orders
	join items on orders.item_code = items.item_code;
    
select orders.*, items.*, (items.price * orders.quantity) as total_for_item
	from orders join items on orders.item_code = items.item_code;

select *, sum(total_for_item) as invoice_total from 
	(select orders.invoice_no, orders.customer_id, orders.item_code as item_code_1, orders.quantity,
	items.item_no, items.item_name, items.item_code as item_code_2, items.price,
    (items.price * orders.quantity) as total_for_item
    from orders
	join items on orders.item_code = items.item_code) as t1
    group by invoice_no;