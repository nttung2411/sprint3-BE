package com.example.sprint3.services.order_food;

import com.example.sprint3.models.OrderFood;
import com.example.sprint3.repositories.OrderFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodServiceImpl implements OrderFoodService{
    @Autowired
    OrderFoodRepository orderFoodRepository;

    @Override
    public void saveOrderFood(OrderFood orderFood) {
        orderFoodRepository.save(orderFood);
    }

    @Override
    public List<OrderFood> getAllOrderFoodByAccount(Integer accountId) {
        return orderFoodRepository.getOrderFoodByAccount(accountId);
    }

    @Override
    public void removeOrderFood(Integer orderFoodId) {
        orderFoodRepository.deleteById(orderFoodId);
    }
}
