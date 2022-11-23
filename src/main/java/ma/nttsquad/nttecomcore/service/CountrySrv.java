package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CountryDto;

import java.util.List;

public interface CountrySrv {

    List<CountryDto> getAllCountries();

    CountryDto getCountryById(Long country_id);

    CountryDto saveCountry(CountryDto countryDto);

    CountryDto updateCountry(Long country_id, CountryDto countryDto);

    void deleteCountry(Long country_id);
}
