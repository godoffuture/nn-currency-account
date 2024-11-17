package com.example.nncurrencyaccount.service;

import com.example.nncurrencyaccount.api.dto.AccountDto;
import com.example.nncurrencyaccount.api.dto.AccountUserDto;
import com.example.nncurrencyaccount.api.dto.RegisterUserDto;
import com.example.nncurrencyaccount.entity.Account;
import com.example.nncurrencyaccount.entity.AccountUser;
import com.example.nncurrencyaccount.repository.AccountRepository;
import com.example.nncurrencyaccount.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurrencyAccountServiceImpl {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    @Transactional
    public Long registerUser(RegisterUserDto registerUserDto) {
        AccountUser newAccountUser = userRepository.save(registerUserDto.toUser());
        Account newAccount = registerUserDto.toAccount();
        newAccount.setUserId(newAccountUser.getId());
        accountRepository.save(newAccount);
        return newAccountUser.getId();
    }

    public AccountUserDto getUserAccountInfo(Long userId) {
        AccountUser accountUser = userRepository.findById(userId).orElse(null);

        if (accountUser == null) {
            return null;
        }

        AccountUserDto accountUserDto = AccountUserDto.from(accountUser);
        accountUserDto.setAccounts(accountRepository.getAccountsByUserId(userId).stream()
                .map(AccountDto::from)
                .toList());

        return accountUserDto;
    }
}
