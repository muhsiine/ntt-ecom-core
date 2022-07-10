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
import ma.nttsquad.nttecomcore.dto.OrderItemDto;
import ma.nttsquad.nttecomcore.service.OrderItemSrv;
import ma.nttsquad.nttecomcore.service.OrderItemSrv;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/orderitems")
@Tag(name = "OrderItem", description = "The Order Item API")
public class OrderItemCtrl {

    final OrderItemSrv orderItemSrv;

    @Operation(summary = "Find all order items", description = "Find All Order Items", tags = "OrderItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<OrderItemDto> getAll(){
        return orderItemSrv.getAllOrderItems();
    }

    @Operation(summary = "Find order item by id", description = "Find Order Item By Id", tags = "OrderItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{orderItem_id}")
    public OrderItemDto getOrderItemById(@PathVariable(name = "orderItem_id") Long orderItem_id) {return orderItemSrv.getOrderItemById(orderItem_id);}

    @Operation(summary = "add new order item", description = "Add New Order Item", tags = "OrderItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveOrderItem(@RequestBody OrderItemDto orderItemDto){
        log.trace("{}", orderItemDto);
        orderItemSrv.saveOrderItem(orderItemDto);
    }

    @Operation(summary = "update order item", description = "Update Order Item", tags = "OrderItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{orderItem_id}")
    public void updateOrderItem(@PathVariable(name = "orderItem_id") Long orderItem_id,@RequestBody OrderItemDto orderItemDto){
        log.trace("{}", orderItemDto);
        orderItemSrv.updateOrderItem(orderItem_id,orderItemDto);
    }

    @Operation(summary = "delete order item", description = "delete Order Item", tags = "OrderItem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{orderItem_id}")
    public void removeOrderItem(@PathVariable(name = "orderItem_id") Long orderItem_id){
        log.trace("{}", orderItem_id);
        orderItemSrv.deleteOrderItem(orderItem_id);
    }
}
