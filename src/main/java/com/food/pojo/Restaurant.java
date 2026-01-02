package com.food.pojo;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String customerType;
    private int deliveryTime;
    private String address;
    private int adminUserId;
    private double rating;
    private boolean isActive;
    private String imagePath;
    
    // Constructors
    public Restaurant() {}
    
    public Restaurant(String name, String address, int adminUserId) {
        this.name = name;
        this.address = address;
        this.adminUserId = adminUserId;
    }
    
    // Getters and Setters
    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }
    
    public int getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(int deliveryTime) { this.deliveryTime = deliveryTime; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public int getAdminUserId() { return adminUserId; }
    public void setAdminUserId(int adminUserId) { this.adminUserId = adminUserId; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    
    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", rating=" + rating + "]";
    }
}