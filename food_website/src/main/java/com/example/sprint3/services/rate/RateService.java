package com.example.sprint3.services.rate;

import com.example.sprint3.models.Rate;

public interface RateService {
    Double getAverageRate(Integer foodId);
    void saveRate(Rate rate);
}
