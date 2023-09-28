package com.example.product_management.service;

import com.example.product_management.model.ProductManagement;
import com.example.product_management.repository.IProductRepository;
import com.example.product_management.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;


public class ProductService implements IProductService {
    IProductRepository repository = new ProductRepository();
    @Override
    public List<ProductManagement> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public void newProduct(ProductManagement productManagement) {
        repository.newProduct(productManagement);
    }

    @Override
    public void update(int id, ProductManagement productManagement) {
        repository.update(id,productManagement);
    }

    @Override
    public void remove(int id) {
        repository.remove(id);
    }

    @Override
    public ProductManagement findById(int id) {
        return repository.findById(id);
    }
}
