package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.Category;
import org.shivam.fooddelivery.exception.RestaurantException;

import java.util.List;

public interface CategoryService {
    public Category createCategory (String name, Long userId) throws RestaurantException;
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException;
    public Category findCategoryById(Long id) throws RestaurantException;
}
