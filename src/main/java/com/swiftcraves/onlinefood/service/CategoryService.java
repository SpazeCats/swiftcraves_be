package com.swiftcraves.onlinefood.service;

import java.util.List;

import com.swiftcraves.onlinefood.model.Category;


public interface CategoryService {

    public Category createCategory(String name, Long userId)throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id)throws Exception;

    public Category findCategoryById(Long id)throws Exception;
}
