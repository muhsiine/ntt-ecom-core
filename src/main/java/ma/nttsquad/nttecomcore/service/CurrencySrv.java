package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CurrencyDTO;
import ma.nttsquad.nttecomcore.dto.ProductDto;

import java.util.List;

public interface CurrencySrv {

    List<CurrencyDTO> getAllCurrencies();

    CurrencyDTO getCurrencyById(Long currencyId);

    CurrencyDTO saveCurrency(CurrencyDTO currencyDto);

    CurrencyDTO updateCurrency(CurrencyDTO currencyDto);

    void deleteCurrency(Long currencyId);
}
