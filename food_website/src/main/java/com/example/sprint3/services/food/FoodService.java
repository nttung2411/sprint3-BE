package com.example.sprint3.services.food;

import com.example.sprint3.models.Food;

import java.util.List;

public interface FoodService {
    List<Food> getAllFood();
    List<Food> getAllFoodByCategory(Integer categoryId);
    List<Food> getFoodByName(String foodName);
    Food getFoodById(Integer foodId);
}
