package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.model.CategoryByLang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryByLangMapper {

    CategoryByLangMapper INSTANCE = Mappers.getMapper(CategoryByLangMapper.class);

    @Mapping(target = "categoryCode", source = "category.categoryCode")
    @Mapping(target = "icon", source = "category.icon")
    @Mapping(target = "id", source = "category.id")
    CategoryByLangDto entityToDto(CategoryByLang categoryByLang);


}
