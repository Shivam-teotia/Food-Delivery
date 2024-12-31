package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.DTO.RestaurantDTO;
import org.shivam.fooddelivery.Model.Restaurant;
import org.shivam.fooddelivery.Model.User;
import org.shivam.fooddelivery.exception.RestaurantException;
import org.shivam.fooddelivery.request.CreateRestaurantRequest;

import java.util.List;

public interface ResataurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
            throws RestaurantException;

    public void deleteRestaurant(Long restaurantId) throws RestaurantException;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant>searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws RestaurantException;

    public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException;

    public RestaurantDTO addToFavorites(Long restaurantId, User user) throws RestaurantException;

    public Restaurant updateRestaurantStatus(Long id)throws RestaurantException;
}
