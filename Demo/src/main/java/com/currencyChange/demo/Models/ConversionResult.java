package com.currencyChange.demo.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConversionResult {

    private String sourceCurrencyCode;

    private String targetCurrencyCode;

    private Double originalAmount;

    private Double convertedAmount;

    public ConversionResult(String sourceCurrencyCode, String targetCurrencyCode, Double originalAmount, Double convertedAmount) {
        this.sourceCurrencyCode = sourceCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
    }
}
