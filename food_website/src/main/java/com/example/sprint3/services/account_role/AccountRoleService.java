package com.example.sprint3.services.account_role;

import com.example.sprint3.models.AccountRole;

import java.util.List;

public interface AccountRoleService {
    List<AccountRole> findAllByAccountId(Integer accountId);
    void setRoleAccount(AccountRole accountRole);
}
