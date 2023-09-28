package com.example.product_management.repository;

import com.example.product_management.model.ProductManagement;

import java.util.List;

public interface IProductRepository {
    List<ProductManagement> findAll();
    void newProduct(ProductManagement productManagement);
    void update(int id, ProductManagement productManagement);
    void remove(int id);
    ProductManagement findById(int id);
}
