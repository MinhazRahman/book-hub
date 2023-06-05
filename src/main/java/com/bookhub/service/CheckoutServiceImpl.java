package com.bookhub.service;

import com.bookhub.dto.PaymentInfo;
import com.bookhub.dto.Purchase;
import com.bookhub.dto.PurchaseResponse;
import com.bookhub.model.Customer;
import com.bookhub.model.Order;
import com.bookhub.model.OrderItem;
import com.bookhub.repository.CustomerRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
//@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService{
    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey){
        this.customerRepository = customerRepository;
        // initialize Stripe API with secret key
        Stripe.apiKey = secretKey;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with order items
        Set<OrderItem> orderItems = purchase.getOrderItems();
        // Replaced lambda 'orderItem -> order.add(orderItem)' with reference
        orderItems.forEach(order::add);

        // populate order with shippingAddress and billingAddress
        order.setShippingAddress(purchase.getShippingAddress());
        order.setBillingAddress(purchase.getBillingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();

        // check if the Customer already exists in the database
        String theEmail = customer.getEmail();

        Customer existingCustomer = customerRepository.findByEmail(theEmail);
        if (existingCustomer != null){
            // if found then assign existingCustomer to customer
            customer = existingCustomer;
        }
        customer.add(order);

        // save data to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        // contains the type of payment method
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "BookHub payment");
        params.put("receipt_email", paymentInfo.getReceiptEmail());

        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {
        // generate a random Universal Unique Identifier(UUID)
        return UUID.randomUUID().toString();
    }
}
