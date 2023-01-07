package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.AddressMapper;
import ma.nttsquad.nttecomcore.mapper.UserMapper;
import ma.nttsquad.nttecomcore.model.repository.AddressRepository;
import ma.nttsquad.nttecomcore.service.AddressSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressSrvImpl implements AddressSrv {

    final  AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAllAddresses() {
        List<AddressDto> allAddressDTO = addressRepository.findAll()
                .stream()
                .map(AddressMapper.INSTANCE::entityToDto)
                .toList();
        if(allAddressDTO == null || allAddressDTO.isEmpty()){
            throw new NttNotFoundException("There's no addresses in database");
        }
        return allAddressDTO;
    }

    @Override
    public AddressDto getAddressById(Long address_id) {
        log.trace("{}", address_id);
        return addressRepository.findById(address_id)
                .map(AddressMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no address with the id '%d'".formatted(address_id)));
    }

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        log.trace("save address: {}",addressDto);
        return AddressMapper.INSTANCE.entityToDto(addressRepository.save(AddressMapper.INSTANCE.dtoToEntity(addressDto)));
    }

    @Override
    public AddressDto updateAddress(Long address_id, AddressDto addressDto) {
        log.trace("update address: {} {}",address_id, addressDto);
        AddressDto address = getAddressById(address_id);
        addressDto.setId(address.getId());
        return AddressMapper.INSTANCE.entityToDto(addressRepository.save(AddressMapper.INSTANCE.dtoToEntity(addressDto)));
    }

    @Override
    public void deleteAddress(Long address_id) {
        log.trace("delete address: {}",address_id);
        addressRepository.deleteById(address_id);
    }

}
