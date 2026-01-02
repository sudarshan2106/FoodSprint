package com.food.dao.impl;

import com.food.dao.UserDAO;
import com.food.pojo.User;
import com.food.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    
    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO User (Username, Password, Email, Phone, Address, Role) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public User getUser(int userId) {
        String query = "SELECT * FROM User WHERE UserId = ?";
        User user = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting user: " + e.getMessage());
        }
        return user;
    }
    
    @Override
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM User WHERE Username = ?";
        User user = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting user by username: " + e.getMessage());
        }
        return user;
    }
    
    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM User WHERE Email = ?";
        User user = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting user by email: " + e.getMessage());
        }
        return user;
    }
    
    @Override
    public boolean updateUser(User user) {
        String query = "UPDATE User SET Username=?, Password=?, Email=?, Phone=?, Address=?, Role=? WHERE UserId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole());
            pstmt.setInt(7, user.getUserId());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM User WHERE UserId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User ORDER BY CreatedDate DESC";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all users: " + e.getMessage());
        }
        return users;
    }
    
    @Override
    public boolean validateUser(String username, String password) {
        String query = "SELECT COUNT(*) FROM User WHERE Username=? AND Password=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error validating user: " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User WHERE Role = ? ORDER BY Username";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, role.toLowerCase());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting users by role: " + e.getMessage());
        }
        return users;
    }
    
    @Override
    public List<User> getAllDeliveryAgents() {
        return getUsersByRole("delivery_agent");
    }
    
    @Override
    public List<User> getAllRestaurantAdmins() {
        return getUsersByRole("restaurant_admin");
    }
    
    @Override
    public List<User> getAllCustomers() {
        return getUsersByRole("customer");
    }
    
    @Override
    public boolean assignDeliveryAgent(int orderId, int deliveryAgentId) {
        String query = "UPDATE Orders SET DeliveryAgentId = ? WHERE OrderId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, deliveryAgentId);
            pstmt.setInt(2, orderId);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error assigning delivery agent: " + e.getMessage());
            return false;
        }
    }
    
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("UserId"));
        user.setUsername(rs.getString("Username"));
        user.setPassword(rs.getString("Password"));
        user.setEmail(rs.getString("Email"));
        user.setPhone(rs.getString("Phone"));
        user.setAddress(rs.getString("Address"));
        user.setRole(rs.getString("Role"));
        user.setCreatedDate(rs.getTimestamp("CreatedDate"));
        return user;
    }
}