package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.AddressMapper;
import ma.nttsquad.nttecomcore.mapper.OrderMapper;
import ma.nttsquad.nttecomcore.model.repository.AddressRepository;
import ma.nttsquad.nttecomcore.model.repository.OrderRepository;
import ma.nttsquad.nttecomcore.service.AddressSrv;
import ma.nttsquad.nttecomcore.service.OrderSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressrSrvImpl implements AddressSrv {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(Long address_id) {
        log.trace("{}", address_id);
        return addressRepository.findById(address_id)
                .map(AddressMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no address with the id '%d'".formatted(address_id)));
    }

    @Override
    public void saveAddress(AddressDto addressDto) {
        log.trace("start save address: {}",addressDto);
        addressRepository.save(AddressMapper.INSTANCE.dtoToEntity(addressDto));
        log.trace("end save address");
    }

    @Override
    public void updateAddress(Long address_id, AddressDto addressDto) {
        log.trace("start update address: {} {}",address_id, addressDto);
        AddressDto address = getAddressById(address_id);
        addressDto.setId(address.getId());
        addressRepository.save(AddressMapper.INSTANCE.dtoToEntity(addressDto));
        log.trace("end update address: {}",address_id);
    }

    @Override
    public void deleteAddress(Long address_id) {
        log.trace("start delete address: {}",address_id);
        addressRepository.deleteById(address_id);
        log.trace("end delete address: {}",address_id);
    }

}
