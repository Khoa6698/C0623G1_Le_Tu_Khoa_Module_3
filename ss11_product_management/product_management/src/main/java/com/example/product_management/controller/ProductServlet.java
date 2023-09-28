package com.example.product_management.controller;

import com.example.product_management.model.ProductManagement;
import com.example.product_management.service.IProductService;
import com.example.product_management.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Product Management", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private final IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                showDeleteForm(req, resp);
                break;
            case "view":
                viewProduct(req, resp);
                break;
            default:
                listProduct(req, resp);
                break;
        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) {
        List<ProductManagement> products = this.productService.findAll();
        req.setAttribute("productManagement", products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/list.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void viewProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductManagement products = this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if (products == null) {
            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("productManagement", products);
            requestDispatcher = req.getRequestDispatcher("/view.jsp");
        }
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductManagement products = this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if (products == null) {
            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("productManagement", products);
            requestDispatcher = req.getRequestDispatcher("/delete.jsp");
        }
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductManagement products = this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if (products == null) {
            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("productManagement", products);
            requestDispatcher = req.getRequestDispatcher("/edit.jsp");
        }
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/create.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action="";
        }
        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                updateProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                break;
        }
    }

//    private void displayProduct(HttpServletRequest req, HttpServletResponse resp) {
//        List<ProductManagement> productList = this.productService.findAll();
//        req.setAttribute("productList",productList);
//        RequestDispatcher requestDispatcher;
//        if(productList == null){
//            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
//        }else {
//            this.productService.findAll();
//            requestDispatcher = req.getRequestDispatcher("/index.jsp");
//        }
//        try {
//            requestDispatcher.forward(req,resp);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductManagement products = this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if (products == null) {
            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            this.productService.remove(id);
            try {
                resp.sendRedirect("/Products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String nameProduct = req.getParameter("nameProduct");
        int price = Integer.parseInt(req.getParameter("price"));
        String describe = req.getParameter("describe");
        ProductManagement products = this.productService.findById(id);
        RequestDispatcher requestDispatcher;
        if (products == null) {
            requestDispatcher = req.getRequestDispatcher("/error-404.jsp");
        } else {
            products.setNameProduct(nameProduct);
            products.setPrice(price);
            products.setDescribe(describe);
            req.setAttribute("productManagement", products);
            req.setAttribute("message", "Product information was updated");
            requestDispatcher = req.getRequestDispatcher("/edit.jsp");
        }
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = (int) (Math.random() * 10000);
        String nameProduct = req.getParameter("nameProduct");
        int price = Integer.parseInt(req.getParameter("price"));
        String describe = req.getParameter("describe");
        ProductManagement products = new ProductManagement(id, nameProduct, price, describe);
        this.productService.newProduct(products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/create.jsp");
        req.setAttribute("message", "New product was create");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}