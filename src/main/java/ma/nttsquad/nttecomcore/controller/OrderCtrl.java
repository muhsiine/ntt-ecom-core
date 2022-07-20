package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.exception.handler.NttExceptionHandler;
import ma.nttsquad.nttecomcore.model.Order;
import ma.nttsquad.nttecomcore.service.OrderSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "The Order API")
public class OrderCtrl {

    private final OrderSrv orderSrv;

    @Operation(summary = "Find all Orders", description = "Return a list of orders", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/all")
    public List<OrderDto> getOrders() {
        return orderSrv.getOrders();
    }

    @Operation(summary = "Find Orders", description = "Find all Orders by User Id", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/findByUserId/{id}")
    public List<OrderDto> getOrdersByUserId(@PathVariable("id") Long userId) {
        return orderSrv.getOrdersByUserId(userId);
    }
}
