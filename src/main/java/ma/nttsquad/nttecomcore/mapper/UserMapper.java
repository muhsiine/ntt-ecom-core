package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);
}
