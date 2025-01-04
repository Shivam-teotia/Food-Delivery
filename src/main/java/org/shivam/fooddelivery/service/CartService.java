package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.Cart;
import org.shivam.fooddelivery.Model.CartItem;
import org.shivam.fooddelivery.exception.CartException;
import org.shivam.fooddelivery.exception.CartItemException;
import org.shivam.fooddelivery.exception.FoodException;
import org.shivam.fooddelivery.exception.UserException;
import org.shivam.fooddelivery.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException;

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

    public Long calculateCartTotals(Cart cart) throws UserException;

    public Cart findCartById(Long id) throws CartException;

    public Cart findCartByUserId(Long userId) throws CartException, UserException;

    public Cart clearCart(Long userId) throws CartException, UserException;
}
