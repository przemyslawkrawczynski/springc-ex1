package com.springacademy.shop.service;

import com.springacademy.shop.domain.Product;
import com.springacademy.shop.repository.ShopTestingRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Service
@Profile("pro")
public class ShopProService {

    @Value("${shop.tax}")
    private double vatTax;
    @Value("${shop.dicount}")
    private double discountValue;
    private ShopTestingRepo shopTestingRepo;

    public ShopProService(ShopTestingRepo shopTestingRepo) {
        this.shopTestingRepo = shopTestingRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        System.out.println("ShopProService");
        showProducts(shopTestingRepo.findAllProducts());
    }

    private void showProducts(List<Product> products) {
        products.stream()
                .map(p -> "Nazwa: " + p.getName() + " | Cena (+VAT): "
                        + p.getPrice()
                        .multiply(new BigDecimal(vatTax))
                        .multiply(new BigDecimal(1-discountValue))
                        .round(new MathContext(4)))
                .forEach(System.out::println);
    }
}
