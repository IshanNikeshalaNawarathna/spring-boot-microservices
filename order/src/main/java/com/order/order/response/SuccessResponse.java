package com.order.order.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.order.order.dto.OrderDTO;
import lombok.Getter;

@Getter
public class SuccessResponse implements Response{

    @JsonUnwrapped
    private final OrderDTO orderDTO;


    public SuccessResponse(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }
}
