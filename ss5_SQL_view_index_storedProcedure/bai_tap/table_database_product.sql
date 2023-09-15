create database demo;
use demo;

create table products(
product_id int primary key auto_increment,
product_code varchar(10) ,
product_name varchar(50) not null,
product_price double check(product_price > 0),
product_amount int not null,
product_description varchar(50),
product_status varchar(50)
);
insert into products(product_code,product_name,product_price,product_amount,product_description,product_status)
values('sp_001','banh',10000,5,'ngon','còn nhiều'),('sp_002','keo',5000,10,'ngọt','còn nhiều'),
      ('sp_003','trái cây',20000,7,'quá ngon','còn nhiều'),('sp_004','nước',15000,9,'đã','còn nhiều');
	