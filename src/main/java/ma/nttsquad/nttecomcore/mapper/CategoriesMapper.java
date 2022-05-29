package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriesMapper {

    CategoriesMapper INSTANCE = Mappers.getMapper(CategoriesMapper.class);

    CategoryDto entityToDto(Category category);

    Category dtoToEntity(CategoryDto categoryDto);
}
