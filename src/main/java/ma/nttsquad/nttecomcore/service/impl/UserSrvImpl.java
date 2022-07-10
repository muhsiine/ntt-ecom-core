package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.AddressMapper;
import ma.nttsquad.nttecomcore.mapper.UserMapper;
import ma.nttsquad.nttecomcore.model.repository.AddressRepository;
import ma.nttsquad.nttecomcore.model.repository.UserRepository;
import ma.nttsquad.nttecomcore.service.AddressSrv;
import ma.nttsquad.nttecomcore.service.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserSrvImpl implements UserSrv {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long user_id) {
        log.trace("{}", user_id);
        return userRepository.findById(user_id)
                .map(UserMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no user with the id '%d'".formatted(user_id)));
    }

    @Override
    public void saveUser(UserDto userDto) {
        log.trace("start save user: {}",userDto);
        userRepository.save(UserMapper.INSTANCE.dtoToEntity(userDto));
        log.trace("end save user");
    }

    @Override
    public void updateUser(Long user_id, UserDto userDto) {
        log.trace("start update user: {} {}",user_id, userDto);
        UserDto user = getUserById(user_id);
        userDto.setId(user.getId());
        userRepository.save(UserMapper.INSTANCE.dtoToEntity(userDto));
        log.trace("end update user: {}",user_id);
    }

    @Override
    public void deleteUser(Long user_id) {
        log.trace("start delete user: {}",user_id);
        userRepository.deleteById(user_id);
        log.trace("end delete user: {}",user_id);
    }
}
