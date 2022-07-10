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
import ma.nttsquad.nttecomcore.dto.AddressDto;
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
    @GetMapping("/user/{user_id}")
    public CartDto getCartByUser(@PathVariable(name = "user_id") Long user_id) {
        return cartSrv.getCartByUser(user_id);
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
    public void saveCart(@RequestBody CartDto cartDto){
        log.trace("{}", cartDto);
        cartSrv.saveCart(cartDto);
    }

    @Operation(summary = "add items to Cart", description = "Add items to Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )

    @PostMapping("/{cartId}/add/cartItems/")
    public void addItemsToCart(@RequestBody List<CartItemDto> cartItems, @PathVariable(name = "cartId") Long cartId){
        log.trace("{},{}", cartItems, cartId);
        cartSrv.addItemsToCart(cartItems, cartId);
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

    @Operation(summary = "Find cart by id", description = "Find Cart By Id", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{cart_id}")
    public CartDto getCartById(@PathVariable(name = "cart_id") Long cart_id) {return cartSrv.getCartById(cart_id);}

    @Operation(summary = "update cart", description = "Update Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{cart_id}")
    public void updateCart(@PathVariable(name = "cart_id") Long cart_id,@RequestBody CartDto cartDto){
        log.trace("{}", cartDto);
        cartSrv.updateCart(cart_id,cartDto);
    }
    @Operation(summary = "delete cart", description = "delete Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{cart_id}")
    public void removeCart(@PathVariable(name = "cart_id") Long cart_id){
        log.trace("{}", cart_id);
        cartSrv.deleteCart(cart_id);
    }
}
