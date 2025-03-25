package com.order.order.controller;

import com.order.order.dto.OrderDTO;
import com.order.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/getallorder")
    private List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping(value = "/addorder")
    private String addOrder(@RequestBody OrderDTO orderDTO){
        return orderService.addOrder(orderDTO);
    }

    @PutMapping(value = "/updateorder")
    private OrderDTO updateOrder(@RequestBody OrderDTO orderDTO){
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping(value = "/deleteorder/{orderId}")
    private String deleteOrder(@PathVariable int orderId){
        return orderService.deleteOrder(orderId);
    }


}
