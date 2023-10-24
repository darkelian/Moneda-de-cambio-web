package com.currencyChange.demo.Controllers;


import com.currencyChange.demo.Models.Currency;
import com.currencyChange.demo.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    // Obtener todas las monedas
    @GetMapping
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    // Obtener una moneda por ID
    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
        return currencyService.getCurrencyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva moneda
    @PostMapping
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency) {
        Currency savedCurrency = currencyService.saveCurrency(currency);
        return ResponseEntity.ok(savedCurrency);
    }

    // Actualizar una moneda
    @PutMapping("/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable Long id, @RequestBody Currency currency) {
        return currencyService.updateCurrency(id, currency)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una moneda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        if (currencyService.deleteCurrency(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

