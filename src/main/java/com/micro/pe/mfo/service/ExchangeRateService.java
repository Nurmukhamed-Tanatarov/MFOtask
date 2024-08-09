package com.micro.pe.mfo.service;

import com.micro.pe.mfo.entity.ExchangeRate;
import com.micro.pe.mfo.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "f157b20ca4c546988f23cffb468812c7";
    private final String urlTemplate = "https://api.twelvedata.com/time_series?symbol=%s&interval=1day&apikey=%s";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<String> getLatestRate(String currencyPair) {
        String url = String.format(urlTemplate, currencyPair, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;
    }

    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency, LocalDateTime date) {
        String currencyPair = fromCurrency + "/" + toCurrency;
        ResponseEntity<String> response = getLatestRate(currencyPair);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode values = root.path("values");
                if (values.isArray() && values.size() > 0) {
                    JsonNode latestRate = values.get(values.size() - 1);
                    String rateString = latestRate.path("close").asText();
                    return new BigDecimal(rateString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return BigDecimal.ONE;
    }
}
