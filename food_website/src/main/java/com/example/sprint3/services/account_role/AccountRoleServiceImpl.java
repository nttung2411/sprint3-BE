package com.example.sprint3.services.account_role;

import com.example.sprint3.models.AccountRole;
import com.example.sprint3.repositories.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRoleServiceImpl implements AccountRoleService{
    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Override
    public List<AccountRole> findAllByAccountId(Integer accountId) {
        return accountRoleRepository.findAllByAccountId(accountId);
    }
}
