package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.Category;
import org.shivam.fooddelivery.Model.Restaurant;
import org.shivam.fooddelivery.Repository.CategoryRepository;
import org.shivam.fooddelivery.exception.RestaurantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ResataurantService restaurantService;

    @Override
    public Category createCategory(String name, Long userId) throws RestaurantException {
        Restaurant restaurant=restaurantService.getRestaurantsByUserId(userId);
        Category createdCategory=new Category();

        createdCategory.setName(name);
        createdCategory.setRestaurant(restaurant);
        return categoryRepository.save(createdCategory);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        return categoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Category findCategoryById(Long id) throws RestaurantException {
        Optional<Category> opt=categoryRepository.findById(id);

        if(opt.isEmpty()) {
            throw new RestaurantException("category not exist with id "+id);
        }

        return opt.get();
    }
}
