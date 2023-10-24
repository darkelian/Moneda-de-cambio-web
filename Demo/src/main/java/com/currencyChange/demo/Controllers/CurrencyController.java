package com.currencyChange.demo.Controllers;


import com.currencyChange.demo.Dtos.StandardResponseDTO;
import com.currencyChange.demo.Models.Currency;
import com.currencyChange.demo.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<StandardResponseDTO> getAllCurrencies() {
        List<Currency> currencies = currencyService.getAllCurrencies();
        if (currencies != null && !currencies.isEmpty()) {
            return new ResponseEntity<>(
                new StandardResponseDTO().FullSuccess(currencies),
                HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                new StandardResponseDTO().FailSuccess("No currencies found"),
                HttpStatus.OK);
        }
    }

    // Obtener una moneda por ID
    @GetMapping(params = "id")
    public ResponseEntity<StandardResponseDTO> getCurrencyById(@RequestParam Long id) {
        return currencyService.getCurrencyById(id)
                .map(currency -> ResponseEntity.ok(new StandardResponseDTO().FullSuccess(currency)))
                .orElse(ResponseEntity.ok(new StandardResponseDTO().FailSuccess("Currency not found")));
    }
    

    // Crear una nueva moneda
    @PostMapping
    public ResponseEntity<StandardResponseDTO> createCurrency(@RequestBody Currency currency) {
        Currency savedCurrency = currencyService.saveCurrency(currency);
        if (savedCurrency != null) {
            return ResponseEntity.ok(new StandardResponseDTO().FullSuccess(currency));
        } else {
            return ResponseEntity.ok(new StandardResponseDTO().FailSuccess("Failed to save currency"));
        }
    }

    // Actualizar una moneda
    @PutMapping
    public ResponseEntity<StandardResponseDTO> updateCurrency(@RequestParam Long id, @RequestBody Currency currency) {
        return currencyService.updateCurrency(id, currency)
                .map(updatedCurrency -> ResponseEntity.ok(new StandardResponseDTO().FullSuccess(currency)))
                .orElse(ResponseEntity.ok(new StandardResponseDTO().FailSuccess("Failed to update currency")));
    }

    // Eliminar una moneda
    @DeleteMapping
    public ResponseEntity<StandardResponseDTO> deleteCurrency(@RequestParam Long id) {
        if (currencyService.deleteCurrency(id)) {
            return ResponseEntity.ok(new StandardResponseDTO().FullSuccess("Currency deleted successfully"));
        } else {
            return ResponseEntity.ok(new StandardResponseDTO().FailSuccess("Failed to delete currency"));
        }
    }
}

