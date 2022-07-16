package ma.nttsquad.nttecomcore.mapper;


import ma.nttsquad.nttecomcore.dto.CountryDto;
import ma.nttsquad.nttecomcore.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryDto entityToDto (Country country);

    Country dtoToEntity (CountryDto countryDto);
}
