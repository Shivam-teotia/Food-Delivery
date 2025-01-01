package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.IngredientCategory;
import org.shivam.fooddelivery.Model.IngredientsItem;
import org.shivam.fooddelivery.exception.RestaurantException;

import java.util.List;

public interface IngredientsService {
    public IngredientCategory createIngredientsCategory(
            String name,Long restaurantId) throws RestaurantException;

    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients(
            Long restaurantId);


    public IngredientsItem createIngredientsItem(Long restaurantId,
                                                 String ingredientName, Long ingredientCategoryId) throws Exception;

    public IngredientsItem updateStoke(Long id) throws Exception;
}
