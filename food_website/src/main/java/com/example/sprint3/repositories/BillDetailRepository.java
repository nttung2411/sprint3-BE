package com.example.sprint3.repositories;

import com.example.sprint3.models.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail , Integer> {
}
