package com.example.nncurrencyaccount.api.dto;

import com.example.nncurrencyaccount.entity.AccountUser;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountUserDto {
    private String name;
    private String surname;
    private List<AccountDto> accounts;

    public static AccountUserDto from(AccountUser accountUser) {
        return AccountUserDto.builder()
                .name(accountUser.getName())
                .surname(accountUser.getSurname())
                .build();
    }
}
