package com.food.pojo;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private int restaurantId;
    private Integer deliveryAgentId; // Can be null
    private Timestamp orderDate;
    private double totalAmount;
    private String status; // PENDING, CONFIRMED, PREPARING, READY_FOR_PICKUP, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
    private String paymentMode;
    private String address;
    // Constructors
    public Order() {}
    
    public Order(int userId, int restaurantId, double totalAmount) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.status = "PENDING";
    }
    
    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    
    public Integer getDeliveryAgentId() { return deliveryAgentId; }
    public void setDeliveryAgentId(Integer deliveryAgentId) { this.deliveryAgentId = deliveryAgentId; }
    
    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
    
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    
    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", userId=" + userId + ", totalAmount=" + totalAmount + ", status=" + status + "]";
    }

	public String setAdress(String address) {
		return address;
		
	}
}