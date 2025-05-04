package com.swiftcraves.onlinefood.service;

import java.util.List;

import com.swiftcraves.onlinefood.model.Order;
import com.swiftcraves.onlinefood.model.User;
import com.swiftcraves.onlinefood.request.OrderRequest;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user);

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId)throws Exception;

    public List<Order> getUsersOrder(Long userId) throws Exception;

    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception;

    public Order findOrderById (Long orderId) throws Exception;
}
