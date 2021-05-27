package com.example.sprint3.repositories;

import com.example.sprint3.models.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {
    @Query("select accountRole from AccountRole accountRole where accountRole.account.accountId = :accountId")
    List<AccountRole> findAllByAccountId(Integer accountId);
}
