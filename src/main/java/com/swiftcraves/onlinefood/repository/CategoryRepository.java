package com.swiftcraves.onlinefood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiftcraves.onlinefood.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category>findByRestaurantId(Long id);
}
