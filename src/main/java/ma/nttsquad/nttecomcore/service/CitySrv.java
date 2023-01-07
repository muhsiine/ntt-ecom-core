package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CityDto;

import java.util.List;

public interface CitySrv {

    List<CityDto> getAllCities();

    CityDto getCityById(Long city_id);

    CityDto saveCity(CityDto cityDto);

    CityDto updateCity(Long city_id, CityDto cityDto);

    void deleteCity(Long city_id);
}
