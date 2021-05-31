package com.example.sprint3.services.account;

import com.example.sprint3.models.Account;
import com.example.sprint3.models.AccountRole;
import com.example.sprint3.models.Role;
import com.example.sprint3.repositories.AccountRepository;
import com.example.sprint3.services.account_role.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountRoleService accountRoleService;

    @Override
    public Account findAccountByName(String accountName) {
        return accountRepository.findAccountByAccountName(accountName);
    }

    @Override
    public Account checkDuplicate(String accountName, String phone, String email, String identity) {
        return accountRepository.checkDuplicate(accountName,phone,email,identity);
    }

    @Override
    public void saveAccount(Account account) {
        account.setAccountPassword(passwordEncoder.encode(account.getAccountPassword()));
        Account accountNew = accountRepository.save(account);
        Role role = new Role();
        role.setRoleId(2);
        role.setRoleName("MEMBER");
        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(accountNew);
        accountRole.setRole(role);
        accountRoleService.setRoleAccount(accountRole);
    }
}
