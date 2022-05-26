package ma.nttsquad.nttecomcore.controller;

import ma.nttsquad.nttecomcore.model.Order;
import ma.nttsquad.nttecomcore.repository.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderCtrl {

    private final OrderRepository orderRepository;

    public OrderCtrl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/all")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
