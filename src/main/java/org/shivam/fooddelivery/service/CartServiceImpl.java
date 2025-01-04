package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.Cart;
import org.shivam.fooddelivery.Model.CartItem;
import org.shivam.fooddelivery.Model.Food;
import org.shivam.fooddelivery.Model.User;
import org.shivam.fooddelivery.Repository.CartItemRepository;
import org.shivam.fooddelivery.Repository.CartRepository;
import org.shivam.fooddelivery.Repository.FoodRepository;
import org.shivam.fooddelivery.exception.CartException;
import org.shivam.fooddelivery.exception.CartItemException;
import org.shivam.fooddelivery.exception.FoodException;
import org.shivam.fooddelivery.exception.UserException;
import org.shivam.fooddelivery.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private FoodRepository menuItemRepository;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);

        Optional<Food> menuItem=menuItemRepository.findById(req.getMenuItemId());
        if(menuItem.isEmpty()) {
            throw new FoodException("Menu Item not exist with id "+req.getMenuItemId());
        }

        Cart cart = findCartByUserId(user.getId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(menuItem.get())) {

                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(menuItem.get());
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setCart(cart);
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*menuItem.get().getPrice());

        CartItem savedItem=cartItemRepository.save(newCartItem);
        cart.getItems().add(savedItem);
        cartRepository.save(cart);

        return savedItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws CartItemException {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()) {
            throw new CartItemException("cart item not exist with id "+cartItemId);
        }
        cartItem.get().setQuantity(quantity);
        cartItem.get().setTotalPrice((cartItem.get().getFood().getPrice()*quantity));
        return cartItemRepository.save(cartItem.get());
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);

        Cart cart = findCartByUserId(user.getId());

        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);

        if(cartItem.isEmpty()) {
            throw new CartItemException("cart item not exist with id "+cartItemId);
        }

        cart.getItems().remove(cartItem.get());
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws UserException {
        Long total = 0L;
        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws CartException {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()) {
            return cart.get();
        }
        throw new CartException("Cart not found with the id "+id);
    }

    @Override
    public Cart findCartByUserId(Long userId) throws CartException, UserException {

        Optional<Cart> opt=cartRepository.findByCustomer_Id(userId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new CartException("cart not found");
    }

    @Override
    public Cart clearCart(Long userId) throws CartException, UserException {
        Cart cart=findCartByUserId(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
