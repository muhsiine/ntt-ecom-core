package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductsMapper {

    ProductsMapper INSTANCE = Mappers.getMapper(ProductsMapper.class);

    ProductDto entityToDto(Product product);
}
