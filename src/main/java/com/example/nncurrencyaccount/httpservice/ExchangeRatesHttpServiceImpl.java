package com.example.nncurrencyaccount.httpservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Service
public class ExchangeRatesHttpServiceImpl {

    @Value("${nbp.exchange-rate.url.usd}")
    private String nbpURL;

    public BigDecimal getExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();
        // TODO handle exception
        ExchangeRates exchangeRates =  restTemplate.getForEntity(URI.create(nbpURL), ExchangeRates.class).getBody();
        assert exchangeRates != null;
        return BigDecimal.valueOf(exchangeRates.rates().get(0).mid());
    }
}

record ExchangeRates(String table, String currency, String code, List<Rate> rates) { }

record Rate(String no, String effectiveDate, double mid) { }
