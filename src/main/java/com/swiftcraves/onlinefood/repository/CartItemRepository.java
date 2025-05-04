package com.swiftcraves.onlinefood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiftcraves.onlinefood.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
