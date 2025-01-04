package org.shivam.fooddelivery.service;

import com.stripe.exception.StripeException;
import org.shivam.fooddelivery.Model.Order;
import org.shivam.fooddelivery.Model.User;
import org.shivam.fooddelivery.exception.CartException;
import org.shivam.fooddelivery.exception.OrderException;
import org.shivam.fooddelivery.exception.RestaurantException;
import org.shivam.fooddelivery.exception.UserException;
import org.shivam.fooddelivery.request.CreateOrderRequest;
import org.shivam.fooddelivery.response.PaymentResponse;

import java.util.List;

public interface OrderService {
    public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException, StripeException;

    public Order updateOrder(Long orderId, String orderStatus) throws OrderException;

    public void cancelOrder(Long orderId) throws OrderException;

    public List<Order> getUserOrders(Long userId) throws OrderException;

    public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException;
}
