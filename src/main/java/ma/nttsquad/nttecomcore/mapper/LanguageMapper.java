package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.LanguageDto;
import ma.nttsquad.nttecomcore.model.Language;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LanguageMapper {

    CategoriesMapper INSTANCE = Mappers.getMapper(CategoriesMapper.class);

    LanguageDto entityToDto(Language language);

    Language dtoToEntity(LanguageDto languageDto);

}
