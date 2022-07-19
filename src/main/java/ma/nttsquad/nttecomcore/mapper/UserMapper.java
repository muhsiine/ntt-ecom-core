package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);
}
