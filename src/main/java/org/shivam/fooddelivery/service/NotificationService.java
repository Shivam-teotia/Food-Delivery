package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.Notification;
import org.shivam.fooddelivery.Model.Order;
import org.shivam.fooddelivery.Model.Restaurant;
import org.shivam.fooddelivery.Model.User;

import java.util.List;

public interface NotificationService {
    public Notification sendOrderStatusNotification(Order order);
    public void sendRestaurantNotification(Restaurant restaurant, String message);
    public void sendPromotionalNotification(User user, String message);

    public List<Notification> findUsersNotification(Long userId);
}
