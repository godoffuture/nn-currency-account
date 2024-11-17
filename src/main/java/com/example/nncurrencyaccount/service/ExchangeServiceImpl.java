package com.example.nncurrencyaccount.service;

import com.example.nncurrencyaccount.api.dto.AccountDto;
import com.example.nncurrencyaccount.api.dto.ExchangeRequest;
import com.example.nncurrencyaccount.entity.Account;
import com.example.nncurrencyaccount.httpservice.ExchangeRatesHttpServiceImpl;
import com.example.nncurrencyaccount.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl {

    private final ExchangeRatesHttpServiceImpl exchangeRatesHttpService;

    private final AccountRepository accountRepository;

    @Transactional
    public List<AccountDto> exchangeCurrency(ExchangeRequest exchangeRequest) {
        BigDecimal exchangeRate = exchangeRatesHttpService.getExchangeRate();

        List<Account> userAccounts = accountRepository.getAccountsByUserId(exchangeRequest.getUserId());

        Account takenCurrencyAccount = userAccounts.stream()
                .filter(account -> exchangeRequest.getFromCurrencyCode().equals(account.getCurrencyCode()))
                .findFirst()
                .orElse(null);

        if (takenCurrencyAccount == null || takenCurrencyAccount.getBalance().compareTo(exchangeRequest.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.OK, "Insufficient funds to exchange " + exchangeRequest.getAmount() + " " + exchangeRequest.getFromCurrencyCode());
        }

        takenCurrencyAccount.setBalance(takenCurrencyAccount.getBalance().subtract(exchangeRequest.getAmount()));

        accountRepository.save(takenCurrencyAccount);

        BigDecimal exchangedAmount = getExchangeAmount(exchangeRequest, exchangeRate);

        Account addCurrencyAccount = userAccounts.stream()
                .filter(account -> exchangeRequest.getToCurrencyCode().equals(account.getCurrencyCode()))
                .findFirst()
                .orElse(new Account(exchangeRequest.getUserId(), exchangeRequest.getToCurrencyCode()));

        addCurrencyAccount.setUserId(exchangeRequest.getUserId());
        addCurrencyAccount.setCurrencyCode(exchangeRequest.getToCurrencyCode());
        addCurrencyAccount.setBalance(Optional.ofNullable(addCurrencyAccount.getBalance())
                .orElse(new BigDecimal(0)).add(exchangedAmount.setScale(2, BigDecimal.ROUND_DOWN)));

        accountRepository.save(addCurrencyAccount);

        return accountRepository.getAccountsByUserId(exchangeRequest.getUserId()).stream()
                .map(AccountDto::from).toList();
    }

    // TODO test
    private BigDecimal getExchangeAmount(ExchangeRequest exchangeRequest, BigDecimal exchangeRate) {
        return switch (exchangeRequest.getFromCurrencyCode()) {
            case PLN -> exchangeRequest.getAmount().divide(exchangeRate, 2, BigDecimal.ROUND_DOWN);
            case USD -> exchangeRequest.getAmount().multiply(exchangeRate);
        };
    }

}
