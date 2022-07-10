package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.service.OrderSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
@Tag(name = "Order", description = "The Order API")
public class OrderCtrl {

    @Autowired
    OrderSrv orderSrv;

    @Operation(summary = "Find all Orders", description = "Find all Orders", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<OrderDto> getOrders() {
        return orderSrv.getOrders();
    }

    @Operation(summary = "Find all Orders by User Id", description = "Find all Orders by User Id", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/user/{id}")
    public List<OrderDto> getOrdersByUserId(@PathVariable("id") Long userId) {
        return orderSrv.getOrdersByUserId(userId);
    }
    @Operation(summary = "Find order by id", description = "Find Order By Id", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{order_id}")
    public OrderDto getOrderById(@PathVariable(name = "order_id") Long order_id) {return orderSrv.getOrderById(order_id);}

    @Operation(summary = "add new order", description = "Add New Order", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveOrder(@RequestBody OrderDto orderDto){
        log.trace("{}", orderDto);
        orderSrv.saveOrder(orderDto);
    }

    @Operation(summary = "update order", description = "Update Order", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{order_id}")
    public void updateOrder(@PathVariable(name = "order_id") Long order_id,@RequestBody OrderDto orderDto){
        log.trace("{}", orderDto);
        orderSrv.updateOrder(order_id,orderDto);
    }

    @Operation(summary = "delete order", description = "delete Order", tags = "Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{order_id}")
    public void removeOrder(@PathVariable(name = "order_id") Long order_id){
        log.trace("{}", order_id);
        orderSrv.deleteOrder(order_id);
    }
}
