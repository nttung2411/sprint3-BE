package com.example.sprint3.services.jwt;

import com.example.sprint3.models.Account;
import com.example.sprint3.models.AccountRole;
import com.example.sprint3.security.AccountPrincipal;
import com.example.sprint3.services.account.AccountService;
import com.example.sprint3.services.account_role.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtAccountDetailService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountService.findAccountByName(accountName);
        if(account == null) {
            throw new UsernameNotFoundException("Account not found with account name: " + accountName);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role;
        for (AccountRole accountRole: accountRoleService.findAllByAccountId(account.getAccountId())){
            role = accountRole.getRole().getRoleName();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(grantedAuthority);
        }

        boolean isEnable;

        if(account.getEnable() == 1){
            isEnable = true;
        } else {
            isEnable = false;
        }

        return new AccountPrincipal(account.getAccountName(),account.getAccountPassword(),isEnable,grantedAuthorities);
    }
}
