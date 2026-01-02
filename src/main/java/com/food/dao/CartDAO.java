package com.food.dao;

import com.food.pojo.Cart;
import com.food.pojo.CartItem;
import java.util.List;

public interface CartDAO {
    // Cart operations
    boolean createCart(Cart cart);
    Cart getCart(int cartId);
    Cart getActiveCartByUser(int userId);
    boolean updateCart(Cart cart);
    boolean deleteCart(int cartId);
    boolean abandonCart(int cartId);
    boolean checkoutCart(int cartId);
    double calculateCartTotal(int cartId);
    
    // Cart item operations with images
    boolean addItemToCart(int cartId, int menuId, String itemName, int quantity, double price, String imagePath);
    boolean addItemToCart(CartItem cartItem);
    boolean updateCartItemQuantity(int cartItemId, int quantity);
    boolean removeItemFromCart(int cartItemId);
    boolean clearCart(int cartId);
    
    // Query operations
    List<CartItem> getCartItems(int cartId);
    List<CartItem> getCartItemsWithDetails(int cartId);
    int getCartItemCount(int cartId);
    boolean isCartEmpty(int cartId);
    boolean itemExistsInCart(int cartId, int menuId);
    int getItemQuantity(int cartId, int menuId);
    
    // Restaurant operations for cart
    Integer getCartRestaurantId(int cartId);
    boolean updateCartRestaurant(int cartId, int restaurantId);
    boolean updateDeliveryAddress(int cartId, String address);
}