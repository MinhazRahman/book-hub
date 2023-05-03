package com.bookhub.service;

import com.bookhub.dto.Purchase;
import com.bookhub.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
