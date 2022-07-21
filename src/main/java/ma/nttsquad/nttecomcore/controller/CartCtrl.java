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
import ma.nttsquad.nttecomcore.exception.NttBadRequestException;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.exception.records.ErrorResponse;
import ma.nttsquad.nttecomcore.model.Cart;
import ma.nttsquad.nttecomcore.service.CartSrv;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/carts")
@Tag(name = "Cart", description = "The Cart API")
public class CartCtrl {

    final CartSrv cartSrv;

    @Operation(summary = "Find all carts", description = "Find all carts", tags = "Cart", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<CartDto>> getAll(){
        return ResponseEntity.ok().body(cartSrv.getAllCarts());
    }

    @Operation(summary = "Find Cart by userId", description = "Find Cart by userId ", tags = "Cart", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok().body(cartSrv.getCartByUserId(userId));
    }

    @Operation(summary = "Find Cart by Id", description = "Find Cart by Id ", tags = "Cart", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(cartSrv.getCartById(id));
    }

    @Operation(summary = "Find cart items of cart", description = "Find cart items of cart ", tags = "Cart", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{cartId}/cartItems")
    public ResponseEntity<List<CartItemDto>> getCartItemsByCartId(@PathVariable(name = "cartId") Long cartId){
        log.trace("{}", cartId);
        return ResponseEntity.ok().body(cartSrv.getCartItemsByCartId(cartId));
    }

    @Operation(summary = "add new Cart", description = "add new Cart", tags = "Cart", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/save")
    public ResponseEntity<CartDto> saveCart(@RequestBody CartDto cartDto) throws Exception {
        log.trace("{}", cartDto);
        try{
            return ResponseEntity.ok().body(cartSrv.saveCart(cartDto));
        }catch(RuntimeException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }catch(Exception ex){
            throw new Exception(ex.getLocalizedMessage());
        }

    }

    @Operation(summary = "add items to Cart", description = "add items to Cart", tags = "Cart", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{cartId}/add/cartItems/")
    public ResponseEntity<CartDto> addItemsToCart(@RequestBody List<CartItemDto> cartItems, @PathVariable(name = "cartId") Long cartId){
        log.trace("{},{}", cartItems, cartId);
        return ResponseEntity.ok().body(cartSrv.addItemsToCart(cartItems, cartId));
    }

    @Operation(summary = "remove items from Cart", description = "remove items from Cart", tags = "Cart", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{cartId}/remove/cartItems")
    public ResponseEntity<CartDto> removeItemsFromCart(@PathVariable(name = "cartId") Long cartId, @RequestBody List<Long> cartItemsId){
        log.trace("{}", cartId);

        return ResponseEntity.ok().body(cartSrv.removeItemsFromCart(cartId, cartItemsId));
    }
}
