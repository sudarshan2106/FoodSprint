package com.food.dao;

import com.food.pojo.User;
import java.util.List;

public interface UserDAO {
    boolean addUser(User user);
    User getUser(int userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
    List<User> getAllUsers();
    boolean validateUser(String username, String password);
    
    // New methods for role-based operations
    List<User> getUsersByRole(String role);
    List<User> getAllDeliveryAgents();
    List<User> getAllRestaurantAdmins();
    List<User> getAllCustomers();
    boolean assignDeliveryAgent(int orderId, int deliveryAgentId);
}