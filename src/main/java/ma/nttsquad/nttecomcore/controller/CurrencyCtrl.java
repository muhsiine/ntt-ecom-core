package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CurrencyDTO;
import ma.nttsquad.nttecomcore.service.CurrencySrv;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/currency")
@Tag(name = "Currency", description = "The Currency API")
public class CurrencyCtrl {

    private final CurrencySrv currencySrv;

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyDTO>> getAll(){
        return new ResponseEntity<>(currencySrv.getAllCurrencies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDTO> getCurrencuById(@PathVariable Long id){
        return new ResponseEntity<>(currencySrv.getCurrencyById(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CurrencyDTO> saveCurrency(@RequestBody CurrencyDTO currencyDTO){
        return new ResponseEntity<>(currencySrv.saveCurrency(currencyDTO),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CurrencyDTO> updateCurrency(@RequestBody CurrencyDTO currencyDTO){
        return new ResponseEntity<>(currencySrv.updateCurrency(currencyDTO),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable Long id){
        currencySrv.deleteCurrency(id);
        return new ResponseEntity<>("Currency has been deleted successfully",HttpStatus.OK);
    }
}
