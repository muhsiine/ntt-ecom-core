package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CityDto;
import ma.nttsquad.nttecomcore.dto.CountryDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CityMapper;
import ma.nttsquad.nttecomcore.mapper.CountryMapper;
import ma.nttsquad.nttecomcore.model.repository.CityRepository;
import ma.nttsquad.nttecomcore.service.CitySrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CitySrvImpl implements CitySrv {

    final CityRepository cityRepository;


    @Override
    public List<CityDto> getAllCities() {
        List<CityDto> AllCityItemDTO = cityRepository.findAll()
                .stream()
                .map(CityMapper.INSTANCE::entityToDto)
                .toList();
        if(AllCityItemDTO == null || AllCityItemDTO.isEmpty()){
            throw new NttNotFoundException("There's no cities in database");
        }
        return AllCityItemDTO;
    }

    @Override
    public CityDto getCityById(Long city_id) {
        log.trace("{}", city_id);
        return cityRepository.findById(city_id)
                .map(CityMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no city with the id '%d'".formatted(city_id)));

    }

    @Override
    public CityDto saveCity(CityDto cityDto) {
        log.trace("save city: {}",cityDto);
        return CityMapper.INSTANCE.entityToDto(cityRepository.save(CityMapper.INSTANCE.dtoToEntity(cityDto)));
    }

    @Override
    public CityDto updateCity(Long city_id, CityDto cityDto) {
        log.trace("update city: {} {}",city_id, cityDto);
        CityDto city = getCityById(city_id);
        cityDto.setId(city.getId());
        return CityMapper.INSTANCE.entityToDto(cityRepository.save(CityMapper.INSTANCE.dtoToEntity(cityDto)));
    }

    @Override
    public void deleteCity(Long city_id) {
        log.trace("delete city: {}",city_id);
        cityRepository.deleteById(city_id);
    }
}
