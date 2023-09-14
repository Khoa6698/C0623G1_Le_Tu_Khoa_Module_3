USE quanlysinhvien;

-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
SELECT 
    *
FROM
    subject
WHERE
    Credit = (SELECT 
            MAX(Credit)
        FROM
            subject);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
SELECT 
    s.*, MAX(m.Mark) AS 'Điểm'
FROM
    subject s
        JOIN
    mark m ON s.SubId = m.SubId
GROUP BY s.SubId;

-- Hiển thị các thông tin sinh viên và điểm trung bình
-- của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
select s.*, avg(m.Mark) as 'Điểm TB'
from student s join mark m on s.StudentId = m.StudentId
group by s.StudentId
order by m.StudentId desc;






