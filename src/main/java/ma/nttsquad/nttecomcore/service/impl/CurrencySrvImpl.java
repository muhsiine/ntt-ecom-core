package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.dto.CurrencyDTO;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CurrencyMapper;
import ma.nttsquad.nttecomcore.model.Currency;
import ma.nttsquad.nttecomcore.model.repository.CurrencyRepository;
import ma.nttsquad.nttecomcore.service.CurrencySrv;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencySrvImpl implements CurrencySrv {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<CurrencyDTO> getAllCurrencies() {
        return currencyRepository.findAll().stream().map(CurrencyMapper.INSTANCE::entityToDto).toList();
    }

    @Override
    public CurrencyDTO getCurrencyById(Long currencyId) {
        return CurrencyMapper.INSTANCE.entityToDto(currencyRepository.findById(currencyId)
                .orElseThrow(() -> new NttNotFoundException("currency with id '%d' not found".formatted(currencyId))));
    }

    @Override
    public CurrencyDTO saveCurrency(CurrencyDTO currencyDto) {
        return CurrencyMapper.INSTANCE.entityToDto(currencyRepository.save(CurrencyMapper.INSTANCE.dtoToEntity(currencyDto)));
    }

    @Override
    public CurrencyDTO updateCurrency(CurrencyDTO currencyDto) {
        CurrencyDTO currency = getCurrencyById(currencyDto.getId());
        currency.setCurrency(currencyDto.getCurrency());
        return CurrencyMapper.INSTANCE.entityToDto(currencyRepository.save(CurrencyMapper.INSTANCE.dtoToEntity(currency)));
    }

    @Override
    public void deleteCurrency(Long currencyId) {
        currencyRepository.deleteById(currencyId);
    }
}
