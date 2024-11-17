package com.example.nncurrencyaccount.api;

import com.example.nncurrencyaccount.api.dto.AccountUserDto;
import com.example.nncurrencyaccount.api.dto.RegisterUserDto;
import com.example.nncurrencyaccount.service.CurrencyAccountServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CurrencyAccountServiceImpl currencyAccountService;

    @PostMapping(path = "/user/register")
    ResponseEntity<Long> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        Long userId = currencyAccountService.registerUser(registerUserDto);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/balance")
    ResponseEntity<AccountUserDto> getBalance(@RequestParam Long userId) {
        if(currencyAccountService.getUserAccountInfo(userId) != null){
            return new ResponseEntity<>(currencyAccountService.getUserAccountInfo(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
