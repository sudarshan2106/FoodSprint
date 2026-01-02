package com.food.dao.impl;

import com.food.dao.MenuDAO;
import com.food.pojo.Menu;
import com.food.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {
    
    @Override
    public boolean addMenu(Menu menu) {
        String query = "INSERT INTO Menu (RestaurantId, ItemName, Description, Price, IsAvailable, ImagePath) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImagePath());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding menu: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Menu getMenu(int menuId) {
        String query = "SELECT * FROM Menu WHERE MenuId = ?";
        Menu menu = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                menu = extractMenuFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting menu: " + e.getMessage());
        }
        return menu;
    }
    
    @Override
    public boolean updateMenu(Menu menu) {
        String query = "UPDATE Menu SET RestaurantId=?, ItemName=?, Description=?, Price=?, IsAvailable=?, ImagePath=? WHERE MenuId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImagePath());
            pstmt.setInt(7, menu.getMenuId());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating menu: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteMenu(int menuId) {
        String query = "DELETE FROM Menu WHERE MenuId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, menuId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting menu: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Menu> getAllMenu() {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM Menu ORDER BY ItemName";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                menus.add(extractMenuFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all menus: " + e.getMessage());
        }
        return menus;
    }
    
    @Override
    public List<Menu> getMenuByRestaurant(int restaurantId) {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM Menu WHERE RestaurantId = ? ORDER BY ItemName";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                menus.add(extractMenuFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting menu by restaurant: " + e.getMessage());
        }
        return menus;
    }
    
    @Override
    public List<Menu> getAvailableMenuByRestaurant(int restaurantId) {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM Menu WHERE RestaurantId = ? AND IsAvailable = TRUE ORDER BY ItemName";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                menus.add(extractMenuFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting available menu by restaurant: " + e.getMessage());
        }
        return menus;
    }
    
    private Menu extractMenuFromResultSet(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setMenuId(rs.getInt("MenuId"));
        menu.setRestaurantId(rs.getInt("RestaurantId"));
        menu.setItemName(rs.getString("ItemName"));
        menu.setDescription(rs.getString("Description"));
        menu.setPrice(rs.getDouble("Price"));
        menu.setAvailable(rs.getBoolean("IsAvailable"));
        menu.setImagePath(rs.getString("ImagePath"));
        return menu;
    }
}