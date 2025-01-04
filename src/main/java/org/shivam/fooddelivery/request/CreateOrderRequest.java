package org.shivam.fooddelivery.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shivam.fooddelivery.Model.Address;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private Long restaurantId;

    private Address deliveryAddress;
}
