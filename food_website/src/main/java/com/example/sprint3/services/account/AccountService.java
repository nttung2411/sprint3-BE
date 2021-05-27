package com.example.sprint3.services.account;

import com.example.sprint3.models.Account;

public interface AccountService {
    Account findAccountByName(String accountName);

    Account checkDuplicate(String accountName, String phone, String email, String identity);

    void saveAccount(Account account);
}
