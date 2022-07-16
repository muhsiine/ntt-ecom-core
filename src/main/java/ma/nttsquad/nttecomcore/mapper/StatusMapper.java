package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.StatusDto;
import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.model.Status;
import ma.nttsquad.nttecomcore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusMapper {

    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    StatusDto entityToDto(Status user);

    Status dtoToEntity(StatusDto userDto);
}
