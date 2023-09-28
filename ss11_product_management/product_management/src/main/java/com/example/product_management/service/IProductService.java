package com.example.product_management.service;

import com.example.product_management.model.ProductManagement;

import java.util.List;

public interface IProductService {
    List<ProductManagement> findAll();
    void newProduct(ProductManagement productManagement);
    void update(int id, ProductManagement productManagement);
    void remove(int id);
    ProductManagement findById(int id);
//    ProductManagement finByName(String name);
}
