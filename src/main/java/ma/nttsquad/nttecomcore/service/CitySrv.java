package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CityDto;

import java.util.List;

public interface CitySrv {

    List<CityDto> getAllCities();

    CityDto getCityById(Long city_id);

    void saveCity(CityDto cityDto);

    void updateCity(Long city_id, CityDto cityDto);

    void deleteCity(Long city_id);
}
