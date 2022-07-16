package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CountryDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CountryMapper;
import ma.nttsquad.nttecomcore.model.repository.CountryRepository;
import ma.nttsquad.nttecomcore.service.CountrySrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CountrySrvImpl implements CountrySrv {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<CountryDto> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CountryDto getCountryById(Long country_id) {
        log.trace("{}", country_id);
        return countryRepository.findById(country_id)
                .map(CountryMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no country with the id '%d'".formatted(country_id)));
    }

    @Override
    public void saveCountry(CountryDto countryDto) {
        log.trace("start save country: {}",countryDto);
        countryRepository.save(CountryMapper.INSTANCE.dtoToEntity(countryDto));
        log.trace("end save country");
    }

    @Override
    public void updateCountry(Long country_id, CountryDto countryDto) {
        log.trace("start update country: {} {}",country_id, countryDto);
        CountryDto country = getCountryById(country_id);
        countryDto.setId(country.getId());
        countryRepository.save(CountryMapper.INSTANCE.dtoToEntity(countryDto));
        log.trace("end update country: {}",country_id);
    }

    @Override
    public void deleteCountry(Long country_id) {
        log.trace("start delete country: {}",country_id);
        countryRepository.deleteById(country_id);
        log.trace("end delete country: {}",country_id);
    }
}
