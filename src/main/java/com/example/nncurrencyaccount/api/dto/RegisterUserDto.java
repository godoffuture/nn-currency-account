package com.example.nncurrencyaccount.api.dto;

import com.example.nncurrencyaccount.CurrencyCode;
import com.example.nncurrencyaccount.entity.Account;
import com.example.nncurrencyaccount.entity.AccountUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterUserDto {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Surname is required.")
    private String surname;

    @NotBlank(message = "Currency is required.")
    @Pattern(regexp = "PLN", message = "The account should be opened in PLN")
    private String currencyCode;

    @NotNull(message = "Account startup balance is required")
    private BigDecimal balance;

    public AccountUser toUser() {
        AccountUser accountUser = new AccountUser();
        accountUser.setName(name);
        accountUser.setSurname(surname);
        return accountUser;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setCurrencyCode(CurrencyCode.valueOf(currencyCode));
        account.setBalance(balance);
        return account;
    }
}
