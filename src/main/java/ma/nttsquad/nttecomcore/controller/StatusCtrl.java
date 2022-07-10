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
import ma.nttsquad.nttecomcore.dto.StatusDto;
import ma.nttsquad.nttecomcore.service.StatusSrv;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/status")
@Tag(name = "Status", description = "The Status API")
public class StatusCtrl {

    final StatusSrv statusSrv;

    @Operation(summary = "Find all status", description = "Find All Status", tags = "Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StatusDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<StatusDto> getAll(){
        return statusSrv.getAllStatus();
    }

    @Operation(summary = "Find status by id", description = "Find Status By Id", tags = "Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StatusDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{status_id}")
    public StatusDto getStatusById(@PathVariable(name = "status_id") Long status_id) {return statusSrv.getStatusById(status_id);}

    @Operation(summary = "add new status", description = "Add New Status", tags = "Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StatusDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveStatus(@RequestBody StatusDto statusDto){
        log.trace("{}", statusDto);
        statusSrv.saveStatus(statusDto);
    }

    @Operation(summary = "update status", description = "Update Status", tags = "Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StatusDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{status_id}")
    public void updateStatus(@PathVariable(name = "status_id") Long status_id,@RequestBody StatusDto statusDto){
        log.trace("{}", statusDto);
        statusSrv.updateStatus(status_id,statusDto);
    }

    @Operation(summary = "delete status", description = "delete Status", tags = "Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StatusDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{status_id}")
    public void removeStatus(@PathVariable(name = "status_id") Long status_id){
        log.trace("{}", status_id);
        statusSrv.deleteStatus(status_id);
    }
}
