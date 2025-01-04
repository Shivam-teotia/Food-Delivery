package org.shivam.fooddelivery.Repository;

import org.shivam.fooddelivery.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer_Id(Long userId);
}
