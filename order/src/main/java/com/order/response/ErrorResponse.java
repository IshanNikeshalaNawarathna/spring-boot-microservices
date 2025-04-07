package com.order.response;

import lombok.Getter;

@Getter
public class ErrorResponse implements Response{

    private final String errorMessage;


    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
