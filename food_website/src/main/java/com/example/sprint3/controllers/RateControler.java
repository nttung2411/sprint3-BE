package com.example.sprint3.controllers;

import com.example.sprint3.models.Rate;
import com.example.sprint3.services.rate.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RateControler {
    @Autowired
    RateService rateService;

    @PostMapping("/save-rate")
    public ResponseEntity<Void> saveRate(@RequestBody Rate rate) {
        try {
            if (rate == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            rateService.saveRate(rate);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
