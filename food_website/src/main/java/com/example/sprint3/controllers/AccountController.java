package com.example.sprint3.controllers;

import com.example.sprint3.models.Account;
import com.example.sprint3.models.CheckDuplicateRegister;
import com.example.sprint3.models.User;
import com.example.sprint3.services.account.AccountService;
import com.example.sprint3.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;


    @GetMapping("/check-duplicate")
    public ResponseEntity<?> checkDuplicate(@RequestParam String accountName,
                                            @RequestParam String phone,
                                            @RequestParam String email,
                                            @RequestParam String identity) {
        try {
            Account account = accountService.checkDuplicate(accountName, phone, email, identity);
            if (account != null) {
                CheckDuplicateRegister checkDuplicateRegister = new CheckDuplicateRegister();
                checkDuplicateRegister.setAccountName(account.getAccountName());
                checkDuplicateRegister.setPhone(account.getUser().getPhone());
                checkDuplicateRegister.setEmail(account.getUser().getEmail());
                checkDuplicateRegister.setIdentity(account.getUser().getIdentity());

                return new ResponseEntity<>(checkDuplicateRegister, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save-account")
    public ResponseEntity<Void> saveAccount(@RequestBody Account account){
        try {
            if(account == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            User user = userService.saveUser(account.getUser());
            account.setUser(user);
            accountService.saveAccount(account);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
