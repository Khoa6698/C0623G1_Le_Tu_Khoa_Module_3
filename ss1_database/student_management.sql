create database student_management;
use student_management;

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200),
    age INT,
    country VARCHAR(50)
);
insert into student(id,name,age,country)
values(1,'Le Tu Khoa',24,'VN');

CREATE TABLE class (
    id INT PRIMARY KEY AUTO_INCREMENT,
    class_name VARCHAR(50)
);
insert into class(class_name)
values('C0623G1');

CREATE TABLE teachers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    teacher_name VARCHAR(50),
    age INT,
    country VARCHAR(50)
);
insert into teachers(teacher_name,age,country)
values('Hai',18,'VN'),('Cong',19,'LAO');
