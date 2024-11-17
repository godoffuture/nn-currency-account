package com.example.nncurrencyaccount.api;

import com.example.nncurrencyaccount.api.dto.AccountDto;
import com.example.nncurrencyaccount.api.dto.ExchangeRequest;
import com.example.nncurrencyaccount.service.ExchangeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeServiceImpl exchangeService;

    @PostMapping(path = "/exchange")
    ResponseEntity<List<AccountDto>> exchange(@RequestBody ExchangeRequest exchangeRequest) {
        return ResponseEntity.ok(exchangeService.exchangeCurrency(exchangeRequest));
    }

}
