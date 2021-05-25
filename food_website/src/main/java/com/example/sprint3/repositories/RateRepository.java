package com.example.sprint3.repositories;

import com.example.sprint3.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate , Integer> {
    @Query("select avg(rate.point) from Rate rate where rate.food.foodId = :foodId")
    Double getAverageRate(Integer foodId);
}
