package com.food.dao;

import com.food.pojo.Order;
import java.util.List;

public interface OrderDAO {
    boolean addOrder(Order order);
    Order getOrder(int orderId);
    boolean updateOrder(Order order);
    boolean deleteOrder(int orderId);
    List<Order> getAllOrders();
    List<Order> getOrdersByUser(int userId);
    List<Order> getOrdersByRestaurant(int restaurantId);
    boolean updateOrderStatus(int orderId, String status);
    
    // New methods for delivery agents
    List<Order> getOrdersByDeliveryAgent(int deliveryAgentId);
    List<Order> getAvailableOrdersForDelivery();
    boolean assignOrderToDeliveryAgent(int orderId, int deliveryAgentId);
    boolean updateOrderWithDeliveryAgent(int orderId, int deliveryAgentId, String status);
}