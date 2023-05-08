package com.bookhub.service;

import com.bookhub.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<Order> findAll(Pageable pageable);
    Order findById(Long id);

    Page<Order> findByCustomerEmail(String email, Pageable pageable);
}
