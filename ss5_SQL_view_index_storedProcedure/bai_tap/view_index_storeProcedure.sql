USE demo;

-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
CREATE UNIQUE INDEX index_products
ON products(product_id,product_code);
-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
EXPLAIN SELECT *
FROM products;

-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
CREATE VIEW view_product AS
    SELECT 
        product_id, product_code, product_name, product_price, product_status
    FROM
        products;
-- Tiến hành sửa đổi view
UPDATE view_product 
SET 
    product_code = 'sp_005'
WHERE
    product_id = 1;

-- Tiến hành xoá view
DROP VIEW view_product;

-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
delimiter //
CREATE PROCEDURE find_all_product()
BEGIN
SELECT *
FROM products;
END //
delimiter ;
CALL find_all_product();

-- Tạo store procedure thêm một sản phẩm mới
delimiter // 
CREATE PROCEDURE add_prodict(
 product_code VARCHAR (10),
 product_name VARCHAR(50),
 product_price DOUBLE,
 product_amount INT,
 product_description VARCHAR(50),
 product_status VARCHAR(50))
 BEGIN
 INSERT INTO products(product_code,product_name,product_price,product_amount,product_description,product_status)
 VALUES (product_code,product_name,product_price,product_amount,product_description,product_status);
 END //
 delimiter ;

 CALL add_prodict('sp_006','thuoc',18000,10,'kich_thich','còn nhiều');
 
 -- Tạo store procedure sửa thông tin sản phẩm theo id
 delimiter //
 CREATE PROCEDURE update_product(
 id INT,
 product_code VARCHAR (10),
 product_name VARCHAR(50),
 product_price DOUBLE,
 product_amount INT,
 product_description VARCHAR(50),
 product_status VARCHAR(50)
 )
 BEGIN
 UPDATE products
 SET 
 product_code = product_code,
 product_name = product_name,
 product_price = product_price,
 product_amount = product_amount,
 product_description = product_description,
 product_status = product_status
 WHERE product_id = id;
 END //
 delimiter ;
 
 CALL update_product(3,'sp_003','điện thoại',1000000,5,'ĐIỆN thoại đời mới','con nhiều');
 
 -- Tạo store procedure xoá sản phẩm theo id
 delimiter //
 CREATE PROCEDURE delete_product(id INT)
 BEGIN
 DELETE 
 FROM products
 WHERE id = product_id;
 END //
 delimiter ;

