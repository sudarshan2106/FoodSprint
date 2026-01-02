package com.food.dao.impl;

import com.food.dao.RestaurantDAO;
import com.food.pojo.Restaurant;
import com.food.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantDAO {
    
    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        String query = "INSERT INTO Restaurant (Name, CustomerType, DeliveryTime, Address, AdminUserId, Rating, IsActive, ImagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCustomerType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setInt(5, restaurant.getAdminUserId());
            pstmt.setDouble(6, restaurant.getRating());
            pstmt.setBoolean(7, restaurant.isActive());
            pstmt.setString(8, restaurant.getImagePath());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding restaurant: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Restaurant getRestaurant(int restaurantId) {
        String query = "SELECT * FROM Restaurant WHERE RestaurantId = ?";
        Restaurant restaurant = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                restaurant = extractRestaurantFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting restaurant: " + e.getMessage());
        }
        return restaurant;
    }
    
    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        String query = "UPDATE Restaurant SET Name=?, CustomerType=?, DeliveryTime=?, Address=?, AdminUserId=?, Rating=?, IsActive=?, ImagePath=? WHERE RestaurantId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getCustomerType());
            pstmt.setInt(3, restaurant.getDeliveryTime());
            pstmt.setString(4, restaurant.getAddress());
            pstmt.setInt(5, restaurant.getAdminUserId());
            pstmt.setDouble(6, restaurant.getRating());
            pstmt.setBoolean(7, restaurant.isActive());
            pstmt.setString(8, restaurant.getImagePath());
            pstmt.setInt(9, restaurant.getRestaurantId());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating restaurant: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteRestaurant(int restaurantId) {
        String query = "DELETE FROM Restaurant WHERE RestaurantId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, restaurantId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting restaurant: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM Restaurant ORDER BY Name";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                restaurants.add(extractRestaurantFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all restaurants: " + e.getMessage());
        }
        return restaurants;
    }
    
    @Override
    public List<Restaurant> getActiveRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM Restaurant WHERE IsActive = TRUE ORDER BY Name";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                restaurants.add(extractRestaurantFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting active restaurants: " + e.getMessage());
        }
        return restaurants;
    }
    
    @Override
    public List<Restaurant> getRestaurantsByAdmin(int adminUserId) {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM Restaurant WHERE AdminUserId = ? ORDER BY Name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, adminUserId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                restaurants.add(extractRestaurantFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting restaurants by admin: " + e.getMessage());
        }
        return restaurants;
    }
    
    private Restaurant extractRestaurantFromResultSet(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("RestaurantId"));
        restaurant.setName(rs.getString("Name"));
        restaurant.setCustomerType(rs.getString("CustomerType"));
        restaurant.setDeliveryTime(rs.getInt("DeliveryTime"));
        restaurant.setAddress(rs.getString("Address"));
        restaurant.setAdminUserId(rs.getInt("AdminUserId"));
        restaurant.setRating(rs.getDouble("Rating"));
        restaurant.setActive(rs.getBoolean("IsActive"));
        restaurant.setImagePath(rs.getString("ImagePath"));
        return restaurant;
    }
}