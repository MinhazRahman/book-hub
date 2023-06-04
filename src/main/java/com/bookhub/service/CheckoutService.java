package com.bookhub.service;

import com.bookhub.dto.PaymentInfo;
import com.bookhub.dto.Purchase;
import com.bookhub.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
