package org.shivam.fooddelivery.controller;

import org.shivam.fooddelivery.Model.Food;
import org.shivam.fooddelivery.Model.Restaurant;
import org.shivam.fooddelivery.Model.User;
import org.shivam.fooddelivery.exception.FoodException;
import org.shivam.fooddelivery.exception.RestaurantException;
import org.shivam.fooddelivery.exception.UserException;
import org.shivam.fooddelivery.request.CreateFoodRequest;
import org.shivam.fooddelivery.service.FoodService;
import org.shivam.fooddelivery.service.ResataurantService;
import org.shivam.fooddelivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResataurantService restaurantService;

    @Autowired
    private FoodService menuItemService;
    @PostMapping()
    public ResponseEntity<Food> createItem(
            @RequestBody CreateFoodRequest item,
            @RequestHeader("Authorization") String jwt)
            throws FoodException, UserException, RestaurantException {
        User user = userService.findUserProfileByJwt(jwt);
        Restaurant restaurant=restaurantService.findRestaurantById(item.getRestaurantId());
        Food menuItem = menuItemService.createFood(item,item.getCategory(),restaurant);
        return ResponseEntity.ok(menuItem);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
            throws UserException, FoodException {
        User user = userService.findUserProfileByJwt(jwt);

        menuItemService.deleteFood(id);
        return ResponseEntity.ok("Menu item deleted");


    }



    @GetMapping("/search")
    public ResponseEntity<List<Food>> getMenuItemByName(@RequestParam String name)  {
        List<Food> menuItem = menuItemService.searchFood(name);
        return ResponseEntity.ok(menuItem);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvilibilityStatus(
            @PathVariable Long id) throws FoodException {
        Food menuItems= menuItemService.updateAvailibilityStatus(id);
        return ResponseEntity.ok(menuItems);
    }
}
