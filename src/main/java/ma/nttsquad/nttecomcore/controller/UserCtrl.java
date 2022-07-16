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
import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.service.UserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "The User API")
public class UserCtrl {

    @Autowired
    final UserSrv userSrv;

    @Operation(summary = "Find all users", description = "Find All Users", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<UserDto> getAll(){
        return userSrv.getAllUsers();
    }

    @Operation(summary = "Find user by id", description = "Find User By Id", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{user_id}")
    public UserDto getUserById(@PathVariable(name = "user_id") Long user_id) {return userSrv.getUserById(user_id);}

    @Operation(summary = "add new user", description = "Add New User", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveUser(@RequestBody UserDto userDto){
        log.trace("{}", userDto);
        userSrv.saveUser(userDto);
    }

    @Operation(summary = "update user", description = "Update User", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{user_id}")
    public void updateUser(@PathVariable(name = "user_id") Long user_id,@RequestBody UserDto userDto){
        log.trace("{}", userDto);
        userSrv.updateUser(user_id,userDto);
    }

    @Operation(summary = "delete user", description = "delete User", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{user_id}")
    public void removeUser(@PathVariable(name = "user_id") Long user_id){
        log.trace("{}", user_id);
        userSrv.deleteUser(user_id);
    }
}
