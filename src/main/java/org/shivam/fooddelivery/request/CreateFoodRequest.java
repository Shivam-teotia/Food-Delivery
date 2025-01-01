package org.shivam.fooddelivery.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shivam.fooddelivery.Model.Category;
import org.shivam.fooddelivery.Model.IngredientsItem;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;


    private Category category;
    private List<String> images;


    private Long restaurantId;

    private boolean vegetarian;
    private boolean seasonal;


    private List<IngredientsItem> ingredients;

}
