package com.example.sprint3.services.order_food;

import com.example.sprint3.repositories.OrderFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFoodServiceImpl implements OrderFoodService{
    @Autowired
    OrderFoodRepository orderFoodRepository;
}
