CREATE DATABASE enterprise;
USE enterprise;
CREATE TABLE PHIEUXUAT(
SoPX INT PRIMARY KEY AUTO_INCREMENT,
NgayXuat DATE
);

CREATE TABLE VATTU(
MaVTU INT PRIMARY KEY AUTO_INCREMENT,
TenVTU VARCHAR(45)
);

CREATE TABLE CT_PHIEUXUAT(
SoPX INT,
MaVTU INT,
PRIMARY KEY(SoPX,MaVTU),
FOREIGN KEY(SoPX) REFERENCES PHIEUXUAT(SoPX),
FOREIGN KEY(MaVTU) REFERENCES VATTU(MaVTU),
DGXuat VARCHAR(45),
SLXuat INT
);

CREATE TABLE PHIEUNHAP(
SoPN INT PRIMARY KEY AUTO_INCREMENT,
NgayNhap DATE
);

CREATE TABLE CT_PHIEUNHAP(
MaVTU INT,
SoPN INT,
PRIMARY KEY(MaVTU,SoPN),
FOREIGN KEY(MaVTU) REFERENCES VATTU(MaVTU),
FOREIGN KEY(SoPN) REFERENCES PHIEUNHAP(SoPN),
DGNhap VARCHAR(45),
SLNhap INT
);

CREATE TABLE NHACC(
MaNCC INT PRIMARY KEY AUTO_INCREMENT,
TenNCC VARCHAR(45),
DiaChi VARCHAR(45)
);

CREATE TABLE PHONE(
MaNCC INT,
Number_Phone VARCHAR(45),
PRIMARY KEY(MaNCC,Number_Phone),
FOREIGN KEY(MaNCC) REFERENCES NHACC(MaNCC)
);

CREATE TABLE DONDH(
SoDH INT PRIMARY KEY AUTO_INCREMENT,
NgayDH DATE,
MaNCC INT,
FOREIGN KEY(MaNCC) REFERENCES NHACC(MaNCC)
);

CREATE TABLE CT_DONDH(
MaVTU INT,
SoDH INT,
PRIMARY KEY(MaVTU,SoDH),
FOREIGN KEY(MaVTU) REFERENCES VATTU(MaVTU),
FOREIGN KEY(SoDH) REFERENCES DONDH(SoDH)
);