package com.currencyChange.demo.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String code; // Ejemplo: "USD"

    @NotNull
    private String name; // Ejemplo: "Dólar estadounidense"

    @NotNull
    private Double exchangeRate; // Tasa de cambio respecto a una moneda base, por ejemplo, el dólar.

    public Currency(String code, String name, Double exchangeRate) {
        this.code = code;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }
}


