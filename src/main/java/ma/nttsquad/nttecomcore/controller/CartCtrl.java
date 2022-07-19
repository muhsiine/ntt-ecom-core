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
import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.service.CartSrv;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/carts")
@Tag(name = "Cart", description = "The Cart API")
public class CartCtrl {

    final CartSrv cartSrv;

    @Operation(summary = "Find all carts", description = "Find all carts", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<CartDto> getAll(){
        return cartSrv.getAllCarts();
    }

    @Operation(summary = "Find user cart", description = "Find user cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/user/{userId}")
    public CartDto getCartByUserId(@PathVariable(name = "userId") Long userId) {
        return cartSrv.getCartByUserId(userId);
    }

    @Operation(summary = "Find cart items of cart", description = "Find cart items of cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/{cartId}/cartItems")
    public List<CartItemDto> getCartItemsByCartId(@PathVariable(name = "cartId") Long cartId){
        log.trace("{}", cartId);
        return cartSrv.getCartItemsByCartId(cartId);
    }

    @Operation(summary = "add new Cart", description = "Add New Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public CartDto saveCart(@RequestBody CartDto cartDto){
        log.trace("{}", cartDto);
        return cartSrv.saveCart(cartDto);
    }

    @Operation(summary = "add items to Cart", description = "Add items to Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )

    @PostMapping("/{cartId}/add/cartItems/")
    public CartDto addItemsToCart(@RequestBody List<CartItemDto> cartItems, @PathVariable(name = "cartId") Long cartId){
        log.trace("{},{}", cartItems, cartId);
        return cartSrv.addItemsToCart(cartItems, cartId);
    }

    @Operation(summary = "remove items from Cart", description = "remove items from Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )

    @PostMapping("/{cartId}/remove/cartItems")
    public void removeItemsFromCart(@PathVariable(name = "cartId") Long cartId, @RequestBody List<Long> cartItemsId){
        log.trace("{}", cartId);
        cartSrv.removeItemsFromCart(cartId, cartItemsId);
    }
}
