package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductImageMapper {

    ProductImageMapper INSTANCE = Mappers.getMapper(ProductImageMapper.class);

    ProductImageDto entityToDto (ProductImage productImage);

    ProductImage dtoToEntity (ProductImageDto productImageDto);
}
