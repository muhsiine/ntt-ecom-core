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
import ma.nttsquad.nttecomcore.exception.handler.NttExceptionHandler;
import ma.nttsquad.nttecomcore.service.CartSrv;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "The Cart API")
@Slf4j
public class CartCtrl {

    private final CartSrv cartSrv;

    @Operation(summary = "Find all carts", description = "Return a list of carts", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/all")
    public List<CartDto> getAll(){
        return cartSrv.getAllCarts();
    }

    @Operation(summary = "Find user cart", description = "Find cart by user", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/user/{user_id}")
    public CartDto getCartByUser(@PathVariable(name = "user_id") Long user_id) {
        return cartSrv.getCartByUser(user_id);
    }

    @Operation(summary = "Find cart items", description = "Return all items of a cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/{cartId}/cartItems")
    public List<CartItemDto> getCartItemsByCartId(@PathVariable(name = "cartId") Long cartId){
        log.trace("{}", cartId);
        return cartSrv.getCartItemsByCartId(cartId);
    }

    @Operation(summary = "Add Cart", description = "Add New Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @PostMapping("/save")
    public void saveCart(@RequestBody CartDto cartDto){
        log.trace("{}", cartDto);
        cartSrv.saveCart(cartDto);
    }

    @Operation(summary = "Add items", description = "Add items to Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = CartItemDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @PostMapping("/{cartId}/add/cartItems")
    public void addItemsToCart(@RequestBody List<CartItemDto> cartItems, @PathVariable(name = "cartId") Long cartId){
        log.trace("{},{}", cartItems, cartId);
        cartSrv.addItemsToCart(cartItems, cartId);
    }

    @Operation(summary = "Remove items", description = "Remove items from Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @PostMapping("/{cartId}/remove/cartItems")
    public void removeItemsFromCart(@PathVariable(name = "cartId") Long cartId, @RequestBody List<Long> cartItemsId){
        log.trace("{}", cartId);
        cartSrv.removeItemsFromCart(cartId, cartItemsId);
    }
}
