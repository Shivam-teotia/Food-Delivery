package org.shivam.fooddelivery.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemRequest {
    private Long menuItemId;
    private int quantity;
    private List<String> ingredients;

}
