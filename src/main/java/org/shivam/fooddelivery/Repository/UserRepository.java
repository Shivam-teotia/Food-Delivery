package org.shivam.fooddelivery.Repository;

import org.shivam.fooddelivery.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
}
