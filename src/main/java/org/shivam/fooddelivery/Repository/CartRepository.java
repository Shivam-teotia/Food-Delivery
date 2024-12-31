package org.shivam.fooddelivery.Repository;

import org.shivam.fooddelivery.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
