package com.example.demo.controller;

import com.example.demo.modol.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CustomerServlet", value = "")
public class CustomerServlet extends HttpServlet {
    private static List<Customer> customerList;
    static {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1,"Mai Văn Hoàn","1983-08-20","Hà Nội","https://chungkhoantaichinh.vn/wp-content/uploads/2022/12/avatar-meo-cute-de-thuong-01.jpg"));
        customerList.add(new Customer(2,"Nguyễn Văn Nam","1983-08-21","Bắc Giang","https://chungkhoantaichinh.vn/wp-content/uploads/2022/12/avatar-meo-cute-de-thuong-02.jpg"));
        customerList.add(new Customer(3,"Nguyễn Thái Hòa","1983-08-22","Nam Định","https://chungkhoantaichinh.vn/wp-content/uploads/2022/12/avatar-meo-cute-de-thuong-03.jpg"));
        customerList.add(new Customer(4,"Trần Đăng Khoa","1983-08-17","Hà Tây","https://chungkhoantaichinh.vn/wp-content/uploads/2022/12/avatar-meo-cute-de-thuong-04.jpg"));
        customerList.add(new Customer(5,"Nguyễn Đình Thi","1983-08-19","Hà Nội","https://chungkhoantaichinh.vn/wp-content/uploads/2022/12/avatar-meo-cute-de-thuong-05.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customerList",customerList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}