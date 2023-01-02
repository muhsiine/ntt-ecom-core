package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.CurrencyDTO;
import ma.nttsquad.nttecomcore.model.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {

    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    CurrencyDTO entityToDto (Currency currency);

    Currency dtoToEntity (CurrencyDTO currencyDTO);
}
