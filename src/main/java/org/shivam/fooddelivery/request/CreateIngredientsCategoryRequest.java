package org.shivam.fooddelivery.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIngredientsCategoryRequest {
    private Long restaurantId;
    private String name;
}
