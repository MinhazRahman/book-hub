package com.bookhub.controller;

import com.bookhub.dto.Purchase;
import com.bookhub.dto.PurchaseResponse;
import com.bookhub.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bookhub/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        if (purchaseResponse == null){
            throw new RuntimeException("Something went wrong for purchase - " + purchase);
        }
        return purchaseResponse;
    }
}
