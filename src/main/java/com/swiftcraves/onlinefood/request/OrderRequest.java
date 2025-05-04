package com.swiftcraves.onlinefood.request;

import com.swiftcraves.onlinefood.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;

}
