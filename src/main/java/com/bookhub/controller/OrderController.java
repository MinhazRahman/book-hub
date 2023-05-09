package com.bookhub.controller;

import com.bookhub.model.Order;
import com.bookhub.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookhub")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public Page<Order> getOrders(Pageable pageable){
        // retrieve all the orders
        return orderService.findAll(pageable);
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable Long orderId){
        Order order = orderService.findById(orderId);

        if (order == null){
            throw new RuntimeException("Order not found with id - " + orderId);
        }
        return order;
    }

    @GetMapping("/orders/findByCustomerEmail/{customerEmail}")
    public Page<Order> getOrdersByCustomerEmail(@PathVariable(name = "customerEmail") String customerEmail, Pageable pageable){
        Page<Order> orders = orderService.findByCustomerEmailOrderByDateCreatedDesc(customerEmail, pageable);

        if (orders == null){
            throw new RuntimeException("Can't find Orders with customer email - " + customerEmail);
        }
        return orders;
    }
}
