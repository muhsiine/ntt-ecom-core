package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CityDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CityMapper;
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

    @Autowired
    CityRepository cityRepository;


    @Override
    public List<CityDto> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(CityMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CityDto getCityById(Long city_id) {
        log.trace("{}", city_id);
        return cityRepository.findById(city_id)
                .map(CityMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no address with the id '%d'".formatted(city_id)));

    }

    @Override
    public void saveCity(CityDto cityDto) {
        log.trace("start save city: {}",cityDto);
        cityRepository.save(CityMapper.INSTANCE.dtoToEntity(cityDto));
        log.trace("end save city");
    }

    @Override
    public void updateCity(Long city_id, CityDto cityDto) {
        log.trace("start update city: {} {}",city_id, cityDto);
        CityDto city = getCityById(city_id);
        cityDto.setId(city.getId());
        cityRepository.save(CityMapper.INSTANCE.dtoToEntity(cityDto));
        log.trace("end update city: {}",city_id);
    }

    @Override
    public void deleteCity(Long city_id) {
        log.trace("start delete city: {}",city_id);
        cityRepository.deleteById(city_id);
        log.trace("end delete city: {}",city_id);
    }
}
