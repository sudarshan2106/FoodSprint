package com.food.dao;

import com.food.pojo.OrderItem;
import java.util.List;

public interface OrderItemDAO {
    boolean addOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(int orderItemId);
    boolean updateOrderItem(OrderItem orderItem);
    boolean deleteOrderItem(int orderItemId);
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByOrder(int orderId);
}