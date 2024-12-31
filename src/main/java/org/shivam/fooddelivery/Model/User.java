package org.shivam.fooddelivery.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shivam.fooddelivery.DTO.RestaurantDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String password;

    private UserRole userRole;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> orders=new ArrayList<>();

    @ElementCollection
    private List<RestaurantDTO> favourites=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    private String status;
}
