package com.bookhub.dto;

import com.bookhub.model.Address;
import com.bookhub.model.Customer;
import com.bookhub.model.Order;
import com.bookhub.model.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
