package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.Category;
import org.shivam.fooddelivery.Model.Food;
import org.shivam.fooddelivery.Model.Restaurant;
import org.shivam.fooddelivery.exception.FoodException;
import org.shivam.fooddelivery.exception.RestaurantException;
import org.shivam.fooddelivery.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category,
                           Restaurant restaurant) throws FoodException, RestaurantException;

    void deleteFood(Long foodId) throws FoodException;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException;

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws FoodException;

    public Food updateAvailibilityStatus(Long foodId) throws FoodException;
}
