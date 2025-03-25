package com.order.order.service;

import com.order.order.dto.OrderDTO;
import com.order.order.model.Orders;
import com.order.order.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderDTO> getAllOrders() {
        List<Orders> ordersList = orderRepo.findAll();
        return modelMapper.map(ordersList, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public String addOrder(OrderDTO orderDTO){
        Orders orders = modelMapper.map(orderDTO, Orders.class);
        orderRepo.save(orders);
        return "Order added successfully";
    }

    public OrderDTO updateOrder(OrderDTO orderDTO){
        Orders orders = modelMapper.map(orderDTO, Orders.class);
        orderRepo.save(orders);
        return orderDTO;
    }

    public String deleteOrder(int orderId){
        orderRepo.deleteById(orderId);
        return "Order deleted successfully";
    }


}
