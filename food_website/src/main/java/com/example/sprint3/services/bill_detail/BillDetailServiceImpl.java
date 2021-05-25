package com.example.sprint3.services.bill_detail;

import com.example.sprint3.repositories.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillDetailServiceImpl implements BillDetailService{
    @Autowired
    BillDetailRepository billDetailRepository;
}
