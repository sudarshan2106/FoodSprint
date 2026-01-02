package com.food.pojo;

public class Menu {
    private int menuId;
    private int restaurantId;
    private String itemName;
    private String description;
    private double price;
    private boolean isAvailable;
    private String imagePath;
    
    // Constructors
    public Menu() {}
    
    public Menu(int restaurantId, String itemName, String description, double price) {
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.isAvailable = true;
    }
    
    // Getters and Setters
    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }
    
    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    
    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", itemName=" + itemName + ", price=" + price + ", available=" + isAvailable + "]";
    }
}