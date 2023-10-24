package com.currencyChange.demo.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ConversionRequest {

    private String sourceCurrencyCode; // Código de la moneda de origen, por ejemplo, "USD"

    private List<String> targetCurrencyCodes; // Códigos de las monedas de destino, por ejemplo, ["COP", "EUR", "BRL"]

    private Double amount; // Monto a convertir

    public ConversionRequest(String sourceCurrencyCode, List<String> targetCurrencyCodes, Double amount) {
        this.sourceCurrencyCode = sourceCurrencyCode;
        this.targetCurrencyCodes = targetCurrencyCodes;
        this.amount = amount;
    }
}

