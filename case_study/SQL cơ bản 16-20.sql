use case_study;

-- 16.	Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021.
DELETE FROM nhan_vien
WHERE ma_nhan_vien NOT IN( 
SELECT hd.ma_nhan_vien
FROM hop_dong hd
JOIN nhan_vien nv ON nv.ma_nhan_vien = hd.ma_nhan_vien
WHERE YEAR(hd.ngay_lam_hop_dong) BETWEEN 2019 AND 2021
GROUP BY hd.ma_nhan_vien
);

-- 17.	Cập nhật thông tin những khách hàng có ten_loai_khach từ Platinum lên Diamond,
-- chỉ cập nhật những khách hàng đã từng đặt phòng với Tổng Tiền thanh toán trong năm
-- 2021 là lớn hơn 10.000.000 VNĐ.
UPDATE khach_hang.ma_khach_hang
SET khach_hang.ma_loai_khach = (SELECT 
            loai_khach.ma_loai_khach
        FROM
            loai_khach
        WHERE
            loai_khach.ten_loai_khach = 'Diamond')
WHERE khach_hang.ma_khach_hang IN (
SELECT k.ma_khach_hang,k.ho_ten,l.ten_loai_khach,sum(d.chi_phi_thue+dk.gia*hct.so_luong) as 'Tong'
FROM hop_dong h
JOIN khach_hang k
ON h.ma_khach_hang = k.ma_khach_hang
JOIN loai_khach l 
ON k.ma_loai_khach = l.ma_loai_khach
JOIN dich_vu d
ON h.ma_dich_vu = d.ma_dich_vu
JOIN hop_dong_chi_tiet hct
ON h.ma_hop_dong = hct.ma_hop_dong
JOIN dich_vu_di_kem dk
ON hct.ma_dich_vu_di_kem = dk.ma_dich_vu_di_kem
WHERE l.ten_loai_khach = "Platinum" 
AND YEAR(h.ngay_lam_hop_dong)=2021
GROUP BY k.ho_ten,k.ma_khach_hang,l.ten_loai_khach
HAVING sum(d.chi_phi_thue+dk.gia*hct.so_luong)>1000000);


-- 18.	Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).
SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM hop_dong
WHERE ma_khach_hang IN (
    SELECT kh.ma_khach_hang
    FROM (
        SELECT hd.ma_khach_hang
        FROM hop_dong hd
        WHERE YEAR(hd.ngay_lam_hop_dong) < 2021
    ) AS kh
);

DELETE FROM khach_hang
WHERE ma_khach_hang IN (
    SELECT kh.ma_khach_hang
    FROM (
        SELECT hd.ma_khach_hang
        FROM hop_dong hd
        WHERE YEAR(hd.ngay_lam_hop_dong) < 2021
    ) AS kh
);


SET SQL_SAFE_UPDATES = 1;
SET FOREIGN_KEY_CHECKS = 1;

-- 19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần trong năm 2020 lên gấp đôi.
update dich_vu_di_kem dvdk
set dvdk.gia = dvdk.gia*2
where dvdk.ma_dich_vu_di_kem in (
select ma_dich_vu_di_kem
from hop_dong_chi_tiet hdct
join hop_dong hd on hd.ma_hop_dong = hdct.ma_hop_dong
where year(hd.ngay_lam_hop_dong) < 2020
and hdct.so_luong > 10
group by dvdk.ma_dich_vu_di_kem
);

-- 20.	Hiển thị thông tin của tất cả các nhân viên và khách hàng có trong hệ thống,
-- thông tin hiển thị bao gồm id (ma_nhan_vien, ma_khach_hang, ho_ten, email,
-- so_dien_thoai, ngay_sinh, dia_chi.
SELECT nhan_vien.ma_nhan_vien,
       nhan_vien.ho_ten,
       nhan_vien.email,
       nhan_vien.so_dien_thoai,
       nhan_vien.ngay_sinh,
       nhan_vien.dia_chi
FROM nhan_vien
UNION
SELECT khach_hang.ma_khach_hang,
       khach_hang.ho_ten,
       khach_hang.email,
       khach_hang.so_dien_thoai,
       khach_hang.ngay_sinh,
       khach_hang.dia_chi
FROM khach_hang;
