package com.example.sprint3.services.food;

import com.example.sprint3.models.Food;
import com.example.sprint3.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    FoodRepository foodRepository;

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @Override
    public List<Food> getAllFoodByCategory(Integer categoryId) {
        return foodRepository.getAllByCategoryCategoryId(categoryId);
    }

    @Override
    public List<Food> getFoodByName(String foodName) {
        return foodRepository.getFoodByName(foodName);
    }

    @Override
    public Food getFoodById(Integer foodId) {
        return foodRepository.getFoodById(foodId);
    }
}
