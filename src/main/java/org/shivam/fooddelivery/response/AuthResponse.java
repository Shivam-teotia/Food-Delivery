package org.shivam.fooddelivery.response;

import lombok.Data;
import org.shivam.fooddelivery.Model.UserRole;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserRole userRole;
}
