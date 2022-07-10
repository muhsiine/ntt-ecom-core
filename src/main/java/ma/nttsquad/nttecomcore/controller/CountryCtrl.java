package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CountryDto;
import ma.nttsquad.nttecomcore.service.CountrySrv;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/countries")
@Tag(name = "Country", description = "The Country API")
public class CountryCtrl {

    final CountrySrv countrySrv;

    @Operation(summary = "Find all countries", description = "Find All Countries", tags = "Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CountryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<CountryDto> getAll(){
        return countrySrv.getAllCountries();
    }

    @Operation(summary = "Find country by id", description = "Find Country By Id", tags = "Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CountryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{country_id}")
    public CountryDto getCountryById(@PathVariable(name = "country_id") Long country_id) {return countrySrv.getCountryById(country_id);}

    @Operation(summary = "add new country", description = "Add New Country", tags = "Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CountryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveCountry(@RequestBody CountryDto countryDto){
        log.trace("{}", countryDto);
        countrySrv.saveCountry(countryDto);
    }

    @Operation(summary = "update country", description = "Update Country", tags = "Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CountryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{country_id}")
    public void updateCountry(@PathVariable(name = "country_id") Long country_id,@RequestBody CountryDto countryDto){
        log.trace("{}", countryDto);
        countrySrv.updateCountry(country_id,countryDto);
    }

    @Operation(summary = "delete country", description = "delete Country", tags = "Country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CountryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{country_id}")
    public void removeCountry(@PathVariable(name = "country_id") Long country_id){
        log.trace("{}", country_id);
        countrySrv.deleteCountry(country_id);
    }
}
