CREATE DATABASE quanlybanhang;
USE quanlybanhang;

CREATE TABLE customer(
cID INT PRIMARY KEY AUTO_INCREMENT,
cName VARCHAR(45) NOT NULL,
cAge INT CHECK(cAge >=5 and cAge <=90)
);
insert into customer(cName,cAge)
values ('Minh Quan',10),('Ngoc Oanh',20),('Hong Ha',50);

CREATE TABLE orders(
oID INT PRIMARY KEY AUTO_INCREMENT,
cID INT,
FOREIGN KEY (cID) REFERENCES customer(cID),
oDate DATE,
oTotalPrice VARCHAR(45)
);
insert into orders(cID,oDate,oTotalPrice)
values(1,'2006-03-21',null),(2,'2006-03-23',null),(1,'2006-03-16',null);

CREATE TABLE product(
pID INT PRIMARY KEY AUTO_INCREMENT,
pName VARCHAR(45) NOT NULL,
pPrice VARCHAR(45) CHECK(pPrice > 0)
);
insert into product(pName,pPrice)
values('May Giat',3),('Tu Lanh',5),('Dieu Hoa',7),('Quat',1),('Bep Dien',2);

CREATE TABLE orderdetail(
oID INT,
pID INT,
PRIMARY KEY (oID,pID),
FOREIGN KEY (oID) REFERENCES orders(oID),
FOREIGN KEY (pID) REFERENCES product(pID),
odQTY INT CHECK(odQTY > 0)
);
insert into orderdetail(oID,pID,odQTY)
values(1,1,3),(1,3,7),(1,4,2),(2,1,1),(3,1,8),(2,5,4),(2,3,3);