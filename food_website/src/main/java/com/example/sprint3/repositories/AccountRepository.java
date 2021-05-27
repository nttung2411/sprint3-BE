package com.example.sprint3.repositories;

import com.example.sprint3.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountByAccountName(String accountName);

    @Query("select account from Account  account where account.accountName = :accountName or " +
            "account.user.phone = :phone or " +
            "account.user.email = :email or " +
            "account.user.identity = :identity")
    Account checkDuplicate(String accountName, String phone, String email, String identity);
}
