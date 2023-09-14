use case_study;

-- 2.	Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên
-- bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
SELECT 
    *
FROM
    nhan_vien
WHERE
    CHAR_LENGTH(ho_ten) <= 15
        AND ho_ten REGEXP '^(h|k|t)';

-- 3.	Hiển thị thông tin của tất cả khách hàng có độ tuổi từ 18 đến
-- 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
SELECT 
    *, FLOOR(DATEDIFF(CURDATE(), ngay_sinh) / 365) AS tuoi
FROM
    khach_hang
WHERE
    FLOOR(DATEDIFF(CURDATE(), ngay_sinh) / 365) BETWEEN 18 AND 50
        AND (dia_chi LIKE '%Quảng Trị%'
        OR dia_chi LIKE '%Đà nẵng%');

-- 4.	Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần.
-- Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng.
-- Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
SELECT 
    khach_hang.ma_khach_hang,
    khach_hang.ho_ten,
    COUNT(hop_dong.ma_hop_dong) AS count
FROM
    khach_hang
        JOIN
    hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
        JOIN
    loai_khach ON khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
WHERE
    loai_khach.ten_loai_khach = 'Diamond'
GROUP BY khach_hang.ma_khach_hang;

-- 5.	Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu,
-- ngay_lam_hop_dong, ngay_ket_thuc, tong_tien (Với tổng tiền được tính theo công
-- thức như sau: Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng
-- dich_vu_di_kem, hop_dong_chi_tiet) cho tất cả các khách hàng đã từng đặt phòng.
-- (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra).
SELECT 
    kh.ma_khach_hang,
    kh.ho_ten,
    lk.ten_loai_khach,
    hd.ma_hop_dong,
    dv.ten_dich_vu,
    hd.ngay_lam_hop_dong,
    hd.ngay_ket_thuc,
    (IFNULL(dv.chi_phi_thue, 0) + SUM(IFNULL(hdct.so_luong, 0) * IFNULL(dvdk.gia, 0))) AS 'Tong_tien'
FROM
    khach_hang kh
        JOIN
    loai_khach lk ON kh.ma_loai_khach = lk.ma_loai_khach
        LEFT JOIN
    hop_dong hd ON kh.ma_khach_hang = hd.ma_khach_hang
        LEFT JOIN
    dich_vu dv ON hd.ma_dich_vu = dv.ma_dich_vu
        LEFT JOIN
    hop_dong_chi_tiet hdct ON hd.ma_hop_dong = hdct.ma_hop_dong
        LEFT JOIN
    dich_vu_di_kem dvdk ON hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
GROUP BY kh.ma_khach_hang , hd.ma_hop_dong;