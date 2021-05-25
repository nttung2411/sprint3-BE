package com.example.sprint3.services.rate;

import com.example.sprint3.models.Rate;
import com.example.sprint3.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService{
    @Autowired
    RateRepository rateRepository;

    @Override
    public Double getAverageRate(Integer foodId) {
        return rateRepository.getAverageRate(foodId);
    }

    @Override
    public void saveRate(Rate rate) {
        rateRepository.save(rate);
    }
}
