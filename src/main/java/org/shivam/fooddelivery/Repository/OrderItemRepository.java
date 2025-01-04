package org.shivam.fooddelivery.Repository;

import org.shivam.fooddelivery.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
