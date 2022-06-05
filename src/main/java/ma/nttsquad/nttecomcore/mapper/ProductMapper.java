package ma.nttsquad.nttecomcore.mapper;


import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto entityToDto (Product product);

    Product dtoToEntity (ProductDto productDto);
}
