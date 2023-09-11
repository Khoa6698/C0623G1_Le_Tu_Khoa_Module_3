create database student_management;
use student_management;

create table Student(
 id int,
 name varchar(200),
 age int,
 country varchar(50)
);
insert into Student(id,name,age,country)
values(1,'Le Tu Khoa',24,'VN');
create table Class(
id int primary key auto_increment,
Class_name varchar(50)
);
insert into Class(Class_name)
values('C0623G1');
create table Teachers(
id int primary key auto_increment,
Teacher_name varchar(50),
age int,
country varchar(50)
);
insert into Teachers(Teacher_name,age,country)
values('Hai',18,'VN'),('Cong',19,'LAO');
