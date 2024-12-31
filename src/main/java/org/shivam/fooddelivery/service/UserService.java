package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.User;
import org.shivam.fooddelivery.exception.UserException;

import java.util.List;

public interface UserService {
    public User findUserProfileByJwt(String jwt) throws UserException;

    public User findUserByEmail(String email) throws UserException;
//
//    public List<User> findAllUsers();
//
//    public List<User> getPenddingRestaurantOwner();
//
//    void updatePassword(User user, String newPassword);
//
//    void sendPasswordResetEmail(User user);
}
