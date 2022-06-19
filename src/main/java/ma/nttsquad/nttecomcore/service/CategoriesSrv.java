package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryLangDto;

import java.util.List;

public interface CategoriesSrv {

    List<CategoryLangDto> getAllCategoriesByLang(LangCons langCode);


}
