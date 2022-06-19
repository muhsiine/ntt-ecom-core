package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;

import java.util.List;

public interface CategoriesSrv {

    List<CategoryByLangDto> getAllCategoriesByLang(LangCons langCode);


}
