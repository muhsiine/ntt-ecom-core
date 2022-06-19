package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.CategoryLangDto;
import ma.nttsquad.nttecomcore.model.CategoryLang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryLangMapper {

    CategoryLangMapper INSTANCE = Mappers.getMapper(CategoryLangMapper.class);

    @Mapping(target = "categoryCode", source = "category.categoryCode")
    @Mapping(target = "icon", source = "category.icon")
    CategoryLangDto entityToDto(CategoryLang categoryLang);


}
