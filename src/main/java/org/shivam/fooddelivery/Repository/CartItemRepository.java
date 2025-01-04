package org.shivam.fooddelivery.Repository;

import org.shivam.fooddelivery.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
