package com.swiftcraves.onlinefood.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.sql.results.graph.embeddable.internal.NestedRowProcessingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftcraves.onlinefood.model.Address;
import com.swiftcraves.onlinefood.model.Cart;
import com.swiftcraves.onlinefood.model.CartItem;
import com.swiftcraves.onlinefood.model.Order;
import com.swiftcraves.onlinefood.model.OrderItem;
import com.swiftcraves.onlinefood.model.Restaurant;
import com.swiftcraves.onlinefood.model.User;
import com.swiftcraves.onlinefood.repository.AddressRepository;
import com.swiftcraves.onlinefood.repository.OrderItemRepository;
import com.swiftcraves.onlinefood.repository.OrderRepository;
import com.swiftcraves.onlinefood.repository.UserRepository;
import com.swiftcraves.onlinefood.request.OrderRequest;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user){
            Address shippAddress=order.getDeliveryAddress();
            Address savedAddress=addressRepository.save(shippAddress);

            if(!user.getAddresses().contains(savedAddress)){
                user.getAddresses().add(savedAddress);
                userRepository.save(user);
            }

            Restaurant restaurant=restaurantService.findRestaurantById(order.getRestaurantId());

            Order createdOrder=new Order();
            createdOrder.setCustomer(user);
            //createdOrder.setCreatedAt(new Date());
            createdOrder.setOrderStatus("PENDING");
            createdOrder.setDeliveryAddress(savedAddress);
            createdOrder.setRestaurant(restaurant);

            Cart cart=cartService.findCartByUserId(user.getId());
            List<OrderItem> orderItems=new ArrayList<>();

            for(CartItem cartItem : cart.getItem()){
                OrderItem orderItem =new OrderItem();
                orderItem.setFood(cartItem.getFood());
                orderItem.setIngredients(cartItem.getIngredients());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setTotalPrice(cartItem.getTotalPrice());
                
                OrderItem savedOrderItem=orderItemRepository.save(orderItem);
                orderItems.add(savedOrderItem);
            }
            Long totalPrice=cartService.calculateCartTotals(cart);

            createdOrder.setItems(orderItems);
            createdOrder.setTotalPrice(totalPrice);

            Order savedOrder=orderRepository.save(createdOrder);
            restaurant.getOrders().add(savedOrder);

            return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Order order=findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY") 
        || orderStatus.equals("DELIVERED") 
        || orderStatus.equals("COMPLETED")
        || orderStatus.equals("PENDING")
        ){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
    }
    throw new Exception("Please select a valid order status.");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }
    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }
    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders= orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null){
            orders = orders.stream().filter(order->
            order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return orders;
    }
    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder=orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order not found.");
        }
        return optionalOrder.get();
}
}
