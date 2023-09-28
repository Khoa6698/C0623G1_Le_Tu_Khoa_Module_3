package com.example.product_management.repository;

import com.example.product_management.model.ProductManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository implements IProductRepository {
    private static List<ProductManagement> products;
    static {
        products = new ArrayList<>();
        products.add(new ProductManagement(1,"bánh",5000,"kinh đô"));
        products.add(new ProductManagement(2,"keo",2000,"thái"));
        products.add(new ProductManagement(3,"trái",7000,"sầu riêng"));
        products.add(new ProductManagement(4,"rau",3000,"sạch"));
        products.add(new ProductManagement(5,"nước",10000,"tinh khiết"));
    }

    @Override
    public List<ProductManagement> findAll() {
        return products;
    }

    @Override
    public void newProduct(ProductManagement productManagement) {
        products.add(productManagement);
        productManagement.setId(products.size());
    }

    @Override
    public void update(int id, ProductManagement productManagement) {
        if(findById(id)!=null){
            int index = products.indexOf(findById(id));
            products.set(index,productManagement);
        }
    }

    @Override
    public void remove(int id) {
        if(findById(id)!=null){
            products.remove(findById(id));
        }
    }

    @Override
    public ProductManagement findById(int id) {
        for (ProductManagement p : products){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }
}
