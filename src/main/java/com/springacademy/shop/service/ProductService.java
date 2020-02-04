package com.springacademy.shop.service;

import com.springacademy.shop.domain.Product;
import com.springacademy.shop.repository.ShopTestingRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class ProductService {

    private ShopTestingRepo shopTestingRepo;

    public ProductService(ShopTestingRepo shopTestingRepo) {
        this.shopTestingRepo = shopTestingRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            BigDecimal bigDecimal = new BigDecimal(random.nextInt(300 - 50 + 1) + 50);
            shopTestingRepo.save(new Product("Product_" + (i + 1), bigDecimal));
        }

    }
}


