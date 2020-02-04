package com.springacademy.shop.service;

import com.springacademy.shop.domain.Product;
import com.springacademy.shop.repository.ShopTestingRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private ShopTestingRepo shopTestingRepo;

    public ShopService(ShopTestingRepo shopTestingRepo) {
        this.shopTestingRepo = shopTestingRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        System.out.println("ShopService");
        showProducts(shopTestingRepo.findAllProducts());
    }

    private void showProducts(List<Product> products) {
        products.stream()
                .map(p -> "Nazwa: " + p.getName() + " | Cena : " + p.getPrice())
                .forEach(System.out::println);
    }

}
