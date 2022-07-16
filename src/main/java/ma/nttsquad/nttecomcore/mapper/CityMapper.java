package ma.nttsquad.nttecomcore.mapper;


import ma.nttsquad.nttecomcore.dto.CityDto;
import ma.nttsquad.nttecomcore.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto entityToDto (City city);

    City dtoToEntity (CityDto cityDto);
}
