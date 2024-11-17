package com.example.nncurrencyaccount.api.dto;

import com.example.nncurrencyaccount.CurrencyCode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRequest {
    @NotBlank(message = "Currency is required.")
    private CurrencyCode fromCurrencyCode;

    @NotBlank(message = "Currency is required.")
    private CurrencyCode toCurrencyCode;
    private BigDecimal amount;
    private Long userId;
}
