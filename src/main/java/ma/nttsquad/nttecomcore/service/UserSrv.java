package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.UserDto;

import java.util.List;

public interface UserSrv {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long user_id);

    void saveUser(UserDto userDto);

    void updateUser(Long user_id, UserDto userDto);

    void deleteUser(Long user_id);
}
