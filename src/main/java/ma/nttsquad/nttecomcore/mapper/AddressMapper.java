package ma.nttsquad.nttecomcore.mapper;


import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.model.Address;
import ma.nttsquad.nttecomcore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto entityToDto (Address address);

    Address dtoToEntity (AddressDto addressDto);
}
