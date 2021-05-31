package com.example.sprint3.services.order_food;

import com.example.sprint3.models.OrderFood;

import java.util.List;

public interface OrderFoodService {
    void saveOrderFood(OrderFood orderFood);
    List<OrderFood> getAllOrderFoodByAccount(Integer accountId);
    void removeOrderFood(Integer orderFoodId);
}
