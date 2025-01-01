package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.Category;
import org.shivam.fooddelivery.Model.Food;
import org.shivam.fooddelivery.Model.IngredientsItem;
import org.shivam.fooddelivery.Model.Restaurant;
import org.shivam.fooddelivery.Repository.FoodRepository;
import org.shivam.fooddelivery.exception.FoodException;
import org.shivam.fooddelivery.exception.RestaurantException;
import org.shivam.fooddelivery.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) throws FoodException, RestaurantException {
        Food food = new Food();
        food.setName(request.getName());
        food.setDescription(request.getDescription());
        food.setPrice(request.getPrice());
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setVegetarian(request.isVegetarian());
        food.setSeasonal(request.isSeasonal());
        food.setAvailable(true); // Default availability
        food.setCreationDate(new Date());

        // Add ingredients
        List<IngredientsItem> ingredients = request.getIngredients().stream().map(ingredientDto -> {
            IngredientsItem ingredient = new IngredientsItem();
            ingredient.setName(ingredientDto.getName());
            ingredient.setCategory(ingredientDto.getCategory());
            return ingredient;
        }).collect(Collectors.toList());

        food.setIngredients(ingredients);

        // Save food (cascades save for ingredients)
        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long foodId) throws FoodException {
        Food food=findFoodById(foodId);
        food.setRestaurant(null);;
        foodRepository.delete(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if (isVegetarian) {
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if (isNonveg) {
            foods = filterByNonveg(foods, isNonveg);
        }

        if (isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory!=null && !foodCategory.equals("")) {
            foods = filterByFoodCategory(foods, foodCategory);
        }

        return foods;
    }
    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream()
                .filter(food -> food.isVegetarian() == isVegetarian)
                .collect(Collectors.toList());
    }
    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream()
                .filter(food -> food.isVegetarian() == false)
                .collect(Collectors.toList());
    }
    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream()
                .filter(food -> food.isSeasonal() == isSeasonal)
                .collect(Collectors.toList());
    }
    private List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) {

        return foods.stream()
                .filter(food -> {
                    if (food.getFoodCategory() != null) {
                        return food.getFoodCategory().getName().equals(foodCategory);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        List<Food> items=new ArrayList<>();

        if(keyword!="") {
            System.out.println("keyword -- "+keyword);
            items=foodRepository.searchByNameOrCategory(keyword);
        }

        return items;
    }

    @Override
    public Food findFoodById(Long foodId) throws FoodException {
        Optional<Food> food = foodRepository.findById(foodId);
        if (food.isPresent()) {
            return food.get();
        }
        throw new FoodException("food with id" + foodId + "not found");
    }

    @Override
    public Food updateAvailibilityStatus(Long foodId) throws FoodException {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        foodRepository.save(food);
        return food;
    }
}
