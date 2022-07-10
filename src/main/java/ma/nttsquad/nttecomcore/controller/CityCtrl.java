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
import ma.nttsquad.nttecomcore.dto.CityDto;
import ma.nttsquad.nttecomcore.service.CitySrv;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cities")
@Tag(name = "City", description = "The City API")
public class CityCtrl {

    final CitySrv citySrv;

    @Operation(summary = "Find all cities", description = "Find All Cities", tags = "City")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<CityDto> getAll(){
        return citySrv.getAllCities();
    }

    @Operation(summary = "Find city by id", description = "Find City By Id", tags = "City")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{city_id}")
    public CityDto getCityById(@PathVariable(name = "city_id") Long city_id) {return citySrv.getCityById(city_id);}

    @Operation(summary = "add new city", description = "Add New City", tags = "City")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveCity(@RequestBody CityDto cityDto){
        log.trace("{}", cityDto);
        citySrv.saveCity(cityDto);
    }

    @Operation(summary = "update city", description = "Update City", tags = "City")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{city_id}")
    public void updateCity(@PathVariable(name = "city_id") Long city_id,@RequestBody CityDto cityDto){
        log.trace("{}", cityDto);
        citySrv.updateCity(city_id,cityDto);
    }

    @Operation(summary = "delete city", description = "delete City", tags = "City")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CityDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{city_id}")
    public void removeCity(@PathVariable(name = "city_id") Long city_id){
        log.trace("{}", city_id);
        citySrv.deleteCity(city_id);
    }
}
