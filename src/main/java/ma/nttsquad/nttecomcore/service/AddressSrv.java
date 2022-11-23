package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.OrderDto;

import java.util.List;

public interface AddressSrv {

    List<AddressDto> getAllAddresses();

    AddressDto getAddressById(Long addresss_id);

    AddressDto saveAddress(AddressDto addressDto);

    AddressDto updateAddress(Long addresss_id, AddressDto addressDto);

    void deleteAddress(Long addresss_id);
}
