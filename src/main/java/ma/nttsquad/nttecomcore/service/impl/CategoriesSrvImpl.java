package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryLangDto;
import ma.nttsquad.nttecomcore.mapper.CategoryLangMapper;
import ma.nttsquad.nttecomcore.repository.CategoryLangRepository;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesSrvImpl implements CategoriesSrv {

    private final CategoryLangRepository categoryLangRepository;

    @Override
    public List<CategoryLangDto> getAllCategoriesByLang(LangCons langCode) {
        return categoryLangRepository.findByLangCode(langCode)
                .stream()
                .map(CategoryLangMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }
}
