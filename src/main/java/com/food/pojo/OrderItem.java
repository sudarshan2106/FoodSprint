package com.food.pojo;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int menuId;
    private int quantity;
    private double price;
    private double subTotal;
    
    // Constructors
    public OrderItem() {}
    
    public OrderItem(int orderId, int menuId, int quantity, double price) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = quantity * price;
    }
    
    // Getters and Setters
    public int getOrderItemId() { return orderItemId; }
    public void setOrderItemId(int orderItemId) { this.orderItemId = orderItemId; }
    
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { 
        this.quantity = quantity;
        this.subTotal = this.quantity * this.price;
    }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { 
        this.price = price;
        this.subTotal = this.quantity * this.price;
    }
    
    public double getSubTotal() { return subTotal; }
    
    @Override
    public String toString() {
        return "OrderItem [orderItemId=" + orderItemId + ", menuId=" + menuId + ", quantity=" + quantity + ", subTotal=" + subTotal + "]";
    }
}