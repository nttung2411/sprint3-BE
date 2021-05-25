package com.example.sprint3.services.bill;

import com.example.sprint3.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService{
    @Autowired
    BillRepository billRepository;
}
