package com.example.nncurrencyaccount.api.dto;

import com.example.nncurrencyaccount.CurrencyCode;
import com.example.nncurrencyaccount.entity.Account;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDto {
    private CurrencyCode currencyCode;
    private BigDecimal balance;

    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .currencyCode(account.getCurrencyCode())
                .balance(account.getBalance())
                .build();
    }
}
