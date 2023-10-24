package com.currencyChange.demo.Services;

import com.currencyChange.demo.Models.Currency;
import com.currencyChange.demo.Repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> getCurrencyById(Long id) {
        return currencyRepository.findById(id);
    }

    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Optional<Currency> updateCurrency(Long id, Currency currency) {
        if (currencyRepository.existsById(id)) {
            currency.setId(id);
            return Optional.of(currencyRepository.save(currency));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteCurrency(Long id) {
        if (currencyRepository.existsById(id)) {
            currencyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

