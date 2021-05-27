package com.example.sprint3.controllers;

import com.example.sprint3.configuration.JwtTokenUtil;
import com.example.sprint3.models.Account;
import com.example.sprint3.models.AccountRole;
import com.example.sprint3.security.JwtRequest;
import com.example.sprint3.security.JwtResponse;
import com.example.sprint3.services.account.AccountService;
import com.example.sprint3.services.account_role.AccountRoleService;
import com.example.sprint3.services.jwt.JwtAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtAccountDetailService jwtAccountDetailService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRoleService accountRoleService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest){
        try{
            JwtResponse jwtResponse = login(jwtRequest);
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private JwtResponse login(JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getAccountName(), jwtRequest.getPassword());
        } catch (Exception e) {
            if (e.getMessage().equals("INVALID_CREDENTIALS")) {
                return new JwtResponse(e.getMessage());
            }
        }

        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(jwtRequest.getAccountName());

        String token = null;
        if (userDetails.isEnabled()) {
            token = jwtTokenUtil.generateToken(userDetails);
        }
        JwtResponse jwtResponse = new JwtResponse(token);
        Account account = accountService.findAccountByName(jwtRequest.getAccountName());
        jwtResponse.setAccount(account);
        List<String> roleList = new ArrayList<>();
        for (AccountRole accountRole : accountRoleService.findAllByAccountId(account.getAccountId())) {
            roleList.add(accountRole.getRole().getRoleName());
        }
        jwtResponse.setRoles(roleList);
        return jwtResponse;
    }

    private void authenticate(String accountName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
