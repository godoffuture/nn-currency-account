package com.example.nncurrencyaccount.entity;

import com.example.nncurrencyaccount.CurrencyCode;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    private Long userId;
    private CurrencyCode currencyCode;
    @Column(precision = 10, scale = 2)
    private BigDecimal balance;
    private String version;

    public Account(Long userId, CurrencyCode currencyCode) {
        this.userId = userId;
        this.currencyCode = currencyCode;
    }
}
