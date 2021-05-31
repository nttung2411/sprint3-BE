package com.example.sprint3.repositories;

import com.example.sprint3.models.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFoodRepository extends JpaRepository<OrderFood, Integer> {
    @Query("select orderFood from OrderFood orderFood where orderFood.account.accountId = :accountId")
    List<OrderFood> getOrderFoodByAccount(Integer accountId);
}
