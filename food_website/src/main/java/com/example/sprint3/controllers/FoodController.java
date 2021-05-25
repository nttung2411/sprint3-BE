package com.example.sprint3.controllers;

import com.example.sprint3.models.Food;
import com.example.sprint3.models.FoodDTO;
import com.example.sprint3.services.food.FoodService;
import com.example.sprint3.services.rate.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private RateService rateService;

    @GetMapping("/allfood")
    public ResponseEntity<List<Food>> getAllFood(){
        try {
            List<Food> foodList = foodService.getAllFood();
            return new ResponseEntity<>(foodList , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foodbycategory/{categoryId}")
    public ResponseEntity<List<Food>> getAllFoodByCategory(@PathVariable Integer categoryId){
        try {
            List<Food> foodList = foodService.getAllFoodByCategory(categoryId);
            return new ResponseEntity<>(foodList , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foodbyname/{foodName}")
    public ResponseEntity<List<Food>> getAllFoodByName(@PathVariable String foodName){
        try {
            if (foodName.equals("undefined")){
                foodName = null;
            }
            List<Food> foodList = foodService.getFoodByName(foodName);
            return new ResponseEntity<>(foodList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/food-detail/{foodId}")
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable Integer foodId){
        try {
            Food food = foodService.getFoodById(foodId);
            Double average = rateService.getAverageRate(foodId);
            average = (double) Math.round(average*10)/10;
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setFood(food);
            foodDTO.setRate(average);
            return new ResponseEntity<>(foodDTO , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
