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
import ma.nttsquad.nttecomcore.model.Order;
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

    @Operation(summary = "Find all carts", description = "Find all carts", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<CartDto> getAll(){
        return cartSrv.getAllCarts();
    }

    @Operation(summary = "Find user cart", description = "Find user cart", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/user/{user_id}")
    public CartDto getCartByUser(@PathVariable(name = "user_id") Long user_id) {
        return cartSrv.getCartByUser(user_id);
    }

    @Operation(summary = "Find cart items of cart", description = "Find cart items of cart", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/{cart_id}/cartItems")
    public List<CartItemDto> getCartItemsByCartId(@PathVariable(name = "cart_id") Long cart_id){
        log.trace("{}", cart_id);
        return cartSrv.getCartItemsByCartId(cart_id);
    }

    @Operation(summary = "add new Cart", description = "Add New Cart", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveCart(@RequestBody CartDto cartDto){
        log.trace("{}", cartDto);
        cartSrv.saveCart(cartDto);
    }

    @Operation(summary = "add items to Cart", description = "Add items to Cart", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/cartItems/add/{cart_id}")
    public void addItemsToCart(@RequestBody List<CartItemDto> cartItems, @PathVariable(name = "cart_id") Long cart_id){
        log.trace("{}", cartItems, cart_id);
        cartSrv.addItemsToCart(cartItems, cart_id);
    }

    @Operation(summary = "remove items from Cart", description = "remove items from Cart", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/removeItems/{cart_id}/{cartItem_id}")
    public void removeItemsFromCart(@PathVariable(name = "cart_id") Long cart_id, @PathVariable(name = "cartItem_id") Long cartItem_id){
        log.trace("{}", cart_id, cartItem_id);
        cartSrv.removeItemsFromCard(cart_id, cartItem_id);
    }
}
