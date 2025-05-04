package com.swiftcraves.onlinefood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiftcraves.onlinefood.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
