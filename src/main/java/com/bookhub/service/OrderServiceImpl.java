package com.bookhub.service;

import com.bookhub.model.Order;
import com.bookhub.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Order> findByCustomerEmail(String email, Pageable pageable) {
        return orderRepository.findByCustomerEmail(email, pageable);
    }

    @Override
    public Page<Order> findByCustomerEmailOrderByDateCreatedDesc(String email, Pageable pageable) {
        return orderRepository.findByCustomerEmailOrderByDateCreatedDesc(email, pageable);
    }
}
