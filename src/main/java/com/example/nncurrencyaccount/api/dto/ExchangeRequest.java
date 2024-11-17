package com.example.nncurrencyaccount.api.dto;

import com.example.nncurrencyaccount.util.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRequest {
    private CurrencyCode fromCurrencyCode;
    private CurrencyCode toCurrencyCode;
    private BigDecimal amount;
    private Long userId;
}
