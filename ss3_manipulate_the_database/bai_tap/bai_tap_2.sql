USE quanlybanhang;

-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order.
SELECT 
    *
FROM
    orders;
    
-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách.
SELECT 
    customer.*, product.pName
FROM
    customer
        INNER JOIN
    orders ON customer.cID = orders.cID
        INNER JOIN
    orderdetail ON orders.oID = orderdetail.oID
        INNER JOIN
    product ON orderdetail.pID = product.pID;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào.
SELECT 
    *
FROM
    customer
        LEFT JOIN
    orders ON customer.cID = orders.cID
WHERE
    orders.cID IS NULL;
    
-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn  (giá một hóa đơn được 
-- tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của
-- từng loại được tính = odQTY*pPrice)
SELECT 
    orderdetail.oID,
    orders.oDate,
    orderdetail.odQTY,
    product.pPrice,
    (orderdetail.odQTY * product.pPrice) AS bill
FROM
    orderdetail
        JOIN
    orders ON orderdetail.oID = orders.oID
        JOIN
    product ON orderdetail.pID = product.pID;