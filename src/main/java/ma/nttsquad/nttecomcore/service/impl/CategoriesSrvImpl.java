package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.mapper.CategoryByLangMapper;
import ma.nttsquad.nttecomcore.model.repository.CategoryByLangRepository;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesSrvImpl implements CategoriesSrv {

    private final CategoryByLangRepository categoryByLangRepository;

    @Override
    public List<CategoryByLangDto> getAllCategoriesByLang(LangCons langCode) {
        return categoryByLangRepository.findByLangCode(langCode)
                .stream()
                .map(CategoryByLangMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }
}
