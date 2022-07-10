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
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.service.CartItemSrv;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cartitems")
@Tag(name = "CartItem", description = "The CartItem API")
public class CartItemCtrl {

    final CartItemSrv cartItemSrv;

    @Operation(summary = "Find all cart items", description = "Find All Cart Items", tags = "CartItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<CartItemDto> getAll(){
        return cartItemSrv.getAllCartItems();
    }

    @Operation(summary = "Find cart item by id", description = "Find Cart Item By Id", tags = "CartItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{cartItem_id}")
    public CartItemDto getCartItemById(@PathVariable(name = "cartItem_id") Long cartItem_id) {return cartItemSrv.getCartItemById(cartItem_id);}

    @Operation(summary = "add new cart item", description = "Add New Cart Item", tags = "CartItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveCartItem(@RequestBody CartItemDto cartItemDto){
        log.trace("{}", cartItemDto);
        cartItemSrv.saveCartItem(cartItemDto);
    }

    @Operation(summary = "update cart item", description = "Update Cart Item", tags = "CartItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{cartItem_id}")
    public void updateCartItem(@PathVariable(name = "cartItem_id") Long cartItem_id,@RequestBody CartItemDto cartItemDto){
        log.trace("{}", cartItemDto);
        cartItemSrv.updateCartItem(cartItem_id,cartItemDto);
    }

    @Operation(summary = "delete cart item", description = "delete Cart Item", tags = "CartItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{cartItem_id}")
    public void removeCartItem(@PathVariable(name = "cartItem_id") Long cartItem_id){
        log.trace("{}", cartItem_id);
        cartItemSrv.deleteCartItem(cartItem_id);
    }
}
