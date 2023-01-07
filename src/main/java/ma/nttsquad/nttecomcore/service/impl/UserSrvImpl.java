package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.AddressMapper;
import ma.nttsquad.nttecomcore.mapper.CartMapper;
import ma.nttsquad.nttecomcore.mapper.UserMapper;
import ma.nttsquad.nttecomcore.model.repository.AddressRepository;
import ma.nttsquad.nttecomcore.model.repository.UserRepository;
import ma.nttsquad.nttecomcore.service.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserSrvImpl implements UserSrv {

    final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> allUserDTO = userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::entityToDto)
                .toList();
        if(allUserDTO == null || allUserDTO.isEmpty()){
            throw new NttNotFoundException("There's no users in database");
        }
        return allUserDTO;
    }

    @Override
    public UserDto getUserById(Long user_id) {
        log.trace("{}", user_id);
        return userRepository.findById(user_id)
                .map(UserMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no user with the id '%d'".formatted(user_id)));
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        log.trace("save user: {}",userDto);
        return UserMapper.INSTANCE.entityToDto(userRepository.save(UserMapper.INSTANCE.dtoToEntity(userDto)));
    }

    @Override
    public UserDto updateUser(Long user_id, UserDto userDto) {
        log.trace("update user: {} , {}",user_id, userDto);
        UserDto user = getUserById(user_id);
        userDto.setId(user.getId());
        return UserMapper.INSTANCE.entityToDto(userRepository.save(UserMapper.INSTANCE.dtoToEntity(userDto)));
    }

    @Override
    public void deleteUser(Long user_id) {
        log.trace("delete user: {}",user_id);
        userRepository.deleteById(user_id);
    }
}
