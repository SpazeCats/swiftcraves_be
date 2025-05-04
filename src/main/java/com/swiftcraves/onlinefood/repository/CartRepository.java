package com.swiftcraves.onlinefood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiftcraves.onlinefood.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

        public Cart findByCustomerId(Long userId);
}
