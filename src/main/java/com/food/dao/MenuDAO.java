package com.food.dao;

import com.food.pojo.Menu;
import java.util.List;

public interface MenuDAO {
    boolean addMenu(Menu menu);
    Menu getMenu(int menuId);
    boolean updateMenu(Menu menu);
    boolean deleteMenu(int menuId);
    List<Menu> getAllMenu();
    List<Menu> getMenuByRestaurant(int restaurantId);
    List<Menu> getAvailableMenuByRestaurant(int restaurantId);
}