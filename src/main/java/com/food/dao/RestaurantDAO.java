package com.food.dao;

import com.food.pojo.Restaurant;
import java.util.List;

public interface RestaurantDAO {
    boolean addRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(int restaurantId);
    boolean updateRestaurant(Restaurant restaurant);
    boolean deleteRestaurant(int restaurantId);
    List<Restaurant> getAllRestaurants();
    List<Restaurant> getActiveRestaurants();
    List<Restaurant> getRestaurantsByAdmin(int adminUserId);
}