package com.food.dao.impl;

import com.food.dao.OrderDAO;
import com.food.pojo.Order;
import com.food.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    
    @Override
    public boolean addOrder(Order order) {
        String query = "INSERT INTO Orders (UserId, RestaurantId, TotalAmount, Status, PaymentMode, DeliveryAgentId) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setDouble(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentMode());
            
            if (order.getDeliveryAgentId() != null) {
                pstmt.setInt(6, order.getDeliveryAgentId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            
            int rows = pstmt.executeUpdate();
            
            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    order.setOrderId(rs.getInt(1));
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error adding order: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Order getOrder(int orderId) {
        String query = "SELECT * FROM Orders WHERE OrderId = ?";
        Order order = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                order = extractOrderFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting order: " + e.getMessage());
            e.printStackTrace();
        }
        return order;
    }
    
    @Override
    public boolean updateOrder(Order order) {
        String query = "UPDATE Orders SET UserId=?, RestaurantId=?, DeliveryAgentId=?, TotalAmount=?, Status=?, PaymentMode=? WHERE OrderId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            
            if (order.getDeliveryAgentId() != null) {
                pstmt.setInt(3, order.getDeliveryAgentId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            
            pstmt.setDouble(4, order.getTotalAmount());
            pstmt.setString(5, order.getStatus());
            pstmt.setString(6, order.getPaymentMode());
            pstmt.setInt(7, order.getOrderId());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating order: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deleteOrder(int orderId) {
        String query = "DELETE FROM Orders WHERE OrderId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, orderId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting order: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders ORDER BY OrderDate DESC";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all orders: " + e.getMessage());
            e.printStackTrace();
        }
        return orders;
    }
    
    @Override
    public List<Order> getOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE UserId = ? ORDER BY OrderDate DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting orders by user: " + e.getMessage());
            e.printStackTrace();
        }
        return orders;
    }
    
    @Override
    public List<Order> getOrdersByRestaurant(int restaurantId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE RestaurantId = ? ORDER BY OrderDate DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting orders by restaurant: " + e.getMessage());
            e.printStackTrace();
        }
        return orders;
    }
    
    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        String query = "UPDATE Orders SET Status = ? WHERE OrderId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating order status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Order> getOrdersByDeliveryAgent(int deliveryAgentId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE DeliveryAgentId = ? ORDER BY OrderDate DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, deliveryAgentId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting orders by delivery agent: " + e.getMessage());
            e.printStackTrace();
        }
        return orders;
    }
    
    @Override
    public List<Order> getAvailableOrdersForDelivery() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE Status IN ('READY_FOR_PICKUP') AND DeliveryAgentId IS NULL ORDER BY OrderDate";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting available orders for delivery: " + e.getMessage());
            e.printStackTrace();
        }
        return orders;
    }
    
    @Override
    public boolean assignOrderToDeliveryAgent(int orderId, int deliveryAgentId) {
        String query = "UPDATE Orders SET DeliveryAgentId = ?, Status = 'OUT_FOR_DELIVERY' WHERE OrderId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, deliveryAgentId);
            pstmt.setInt(2, orderId);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error assigning order to delivery agent: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updateOrderWithDeliveryAgent(int orderId, int deliveryAgentId, String status) {
        String query = "UPDATE Orders SET DeliveryAgentId = ?, Status = ? WHERE OrderId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, deliveryAgentId);
            pstmt.setString(2, status);
            pstmt.setInt(3, orderId);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating order with delivery agent: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("OrderId"));
        order.setUserId(rs.getInt("UserId"));
        order.setRestaurantId(rs.getInt("RestaurantId"));
        
        // Handle nullable DeliveryAgentId
        int deliveryAgentId = rs.getInt("DeliveryAgentId");
        if (!rs.wasNull()) {
            order.setDeliveryAgentId(deliveryAgentId);
        } else {
            order.setDeliveryAgentId(null);
        }
        
        order.setOrderDate(rs.getTimestamp("OrderDate"));
        order.setTotalAmount(rs.getDouble("TotalAmount"));
        order.setStatus(rs.getString("Status"));
        order.setPaymentMode(rs.getString("PaymentMode"));
        return order;
    }
}