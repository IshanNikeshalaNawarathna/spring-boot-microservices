package com.order.order.service;

import com.inventory.inventory.dto.InventoryDTO;
import com.order.order.dto.OrderDTO;
import com.order.order.model.Orders;
import com.order.order.repo.OrderRepo;
import com.order.order.response.ErrorResponse;
import com.order.order.response.Response;
import com.order.order.response.SuccessResponse;
import com.product.product.dto.ProductDTO;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Getter
@Service
@Transactional
public class OrderService {

    private final WebClient productWebclient;
    private final WebClient inventoryWebClient;


    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient productWebclient, WebClient inventoryWebclient) {
        this.productWebclient = productWebclient;
        this.inventoryWebClient = inventoryWebclient;
    }


    public List<OrderDTO> getAllOrders() {
        List<Orders> ordersList = orderRepo.findAll();
        return modelMapper.map(ordersList, new TypeToken<List<OrderDTO>>() {
        }.getType());
    }

    public Response addOrder(OrderDTO orderDTO) {

        int itemId = orderDTO.getOrderId();

        try {

            InventoryDTO inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getitembyid/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();

            assert inventoryResponse != null;

            int productId = inventoryResponse.getProductId();

            ProductDTO productResponse = productWebclient.get()
                    .uri(uriBuilder -> uriBuilder.path("/productitemid/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();



            if (inventoryResponse.getQuantity() > 0) {
                assert productResponse != null;
                if(productResponse.getForSale() == 1){
                    Orders orders = modelMapper.map(orderDTO, Orders.class);
                    orderRepo.save(orders);
                }else {
                    return new ErrorResponse("This item is not for sale.");
                }

                return new SuccessResponse(orderDTO);
            } else {
                return new ErrorResponse("Item not available please try later.");
            }


        } catch (WebClientResponseException e) {
            if(e.getStatusCode().is5xxServerError()){
                return new ErrorResponse("Item is not found.");
            }
        }

        return null;

    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Orders orders = modelMapper.map(orderDTO, Orders.class);
        orderRepo.save(orders);
        return orderDTO;
    }

    public String deleteOrder(int orderId) {
        orderRepo.deleteById(orderId);
        return "Order deleted successfully";
    }


}
