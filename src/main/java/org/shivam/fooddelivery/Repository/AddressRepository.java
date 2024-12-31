package org.shivam.fooddelivery.Repository;

import org.shivam.fooddelivery.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
