package com.example.sprint3.repositories;

import com.example.sprint3.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> getAllByCategoryCategoryId(Integer categoryId);

    @Query("select food from Food food where (:foodName is null  or food.foodName like %:foodName%)")
    List<Food> getFoodByName(String foodName);

    @Query("select food from Food food where food.foodId = :foodId")
    Food getFoodById(Integer foodId);
}
