package com.springacademy.shop.repository;

import com.springacademy.shop.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopTestingRepo {

    private List<Product> products = new ArrayList<>();

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> findAllProducts() {
        return products;
    }

}
