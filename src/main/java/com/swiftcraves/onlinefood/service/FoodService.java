package com.swiftcraves.onlinefood.service;

import java.util.List;

import com.swiftcraves.onlinefood.model.Category;
import com.swiftcraves.onlinefood.model.Food;
import com.swiftcraves.onlinefood.model.Restaurant;
import com.swiftcraves.onlinefood.request.CreateFoodRequest;

public interface FoodService {

        public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

        void deleteFood(Long foodId) throws Exception;

        public List<Food> getRestaurantFood(Long restaurantId,
                                            boolean isVegetarian,
                                            boolean isNonveg, 
                                            boolean isSeasonal, 
                                            String foodCategory
        );
public List<Food> searchFood(String keyword);

public Food findFoodById(Long foodId)throws Exception;

public Food updateAvailabilityStatus(Long foodId)throws Exception;
}
