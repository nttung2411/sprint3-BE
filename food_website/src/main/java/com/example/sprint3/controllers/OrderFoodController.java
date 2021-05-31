package com.example.sprint3.controllers;

import com.example.sprint3.models.OrderFood;
import com.example.sprint3.services.order_food.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderFoodController {
    @Autowired
    private OrderFoodService orderFoodService;

    @PostMapping("/save-order")
    public ResponseEntity<Void> saveOrderFood(@RequestBody OrderFood orderFood) {
        try {
            if (orderFood == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderFoodService.saveOrderFood(orderFood);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foodsCart/{accountId}")
    public ResponseEntity<List<OrderFood>> getOrderFood(@PathVariable Integer accountId) {
        try {
            List<OrderFood> orderFoodList = orderFoodService.getAllOrderFoodByAccount(accountId);
            return new ResponseEntity<>(orderFoodList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove-order/{orderFoodId}")
    public ResponseEntity<Void> removeOrderFood(@PathVariable Integer orderFoodId){
        try {
            orderFoodService.removeOrderFood(orderFoodId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
