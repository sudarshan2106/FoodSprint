package com.food.dao.impl;

import com.food.dao.OrderItemDAO;
import com.food.pojo.OrderItem;
import com.food.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {
    
    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        String query = "INSERT INTO OrderItem (OrderId, MenuId, Quantity, Price) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getPrice());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding order item: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public OrderItem getOrderItem(int orderItemId) {
        String query = "SELECT *, (Quantity * Price) AS SubTotal FROM OrderItem WHERE OrderItemId = ?";
        OrderItem orderItem = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, orderItemId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                orderItem = extractOrderItemFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting order item: " + e.getMessage());
        }
        return orderItem;
    }
    
    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        String query = "UPDATE OrderItem SET OrderId=?, MenuId=?, Quantity=?, Price=? WHERE OrderItemId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getPrice());
            pstmt.setInt(5, orderItem.getOrderItemId());
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating order item: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteOrderItem(int orderItemId) {
        String query = "DELETE FROM OrderItem WHERE OrderItemId=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, orderItemId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting order item: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT *, (Quantity * Price) AS SubTotal FROM OrderItem ORDER BY OrderItemId";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                orderItems.add(extractOrderItemFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all order items: " + e.getMessage());
        }
        return orderItems;
    }
    
    @Override
    public List<OrderItem> getOrderItemsByOrder(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT *, (Quantity * Price) AS SubTotal FROM OrderItem WHERE OrderId = ? ORDER BY OrderItemId";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                orderItems.add(extractOrderItemFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting order items by order: " + e.getMessage());
        }
        return orderItems;
    }
    
    private OrderItem extractOrderItemFromResultSet(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(rs.getInt("OrderItemId"));
        orderItem.setOrderId(rs.getInt("OrderId"));
        orderItem.setMenuId(rs.getInt("MenuId"));
        orderItem.setQuantity(rs.getInt("Quantity"));
        orderItem.setPrice(rs.getDouble("Price"));
        return orderItem;
    }
}