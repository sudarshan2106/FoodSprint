package com.food.Main;

import com.food.dao.*;
import com.food.dao.impl.*;
import com.food.pojo.*;
import com.food.util.DBConnection;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO = new UserDAOImpl();
    private static RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
    private static MenuDAO menuDAO = new MenuDAOImpl();
    private static OrderDAO orderDAO = new OrderDAOImpl();
    private static OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║        FOODSPRINT FOOD DELIVERY SYSTEM          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        
        // Test database connection
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Database connection established successfully!\n");
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("❌ ERROR: Could not connect to database!");
            System.out.println("Please check your MySQL configuration.");
            return;
        }
        
        showMainMenu();
        scanner.close();
    }
    
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║                   MAIN MENU                      ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ 1. User Management                               ║");
            System.out.println("║ 2. Restaurant Management                         ║");
            System.out.println("║ 3. Menu Management                               ║");
            System.out.println("║ 4. Order Management                              ║");
            System.out.println("║ 5. Order Item Management                         ║");
            System.out.println("║ 6. View All Data                                 ║");
            System.out.println("║ 7. Demo All Operations                           ║");
            System.out.println("║ 8. Exit                                          ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice (1-8): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    restaurantManagementMenu();
                    break;
                case 3:
                    menuManagementMenu();
                    break;
                case 4:
                    orderManagementMenu();
                    break;
                case 5:
                    orderItemManagementMenu();
                    break;
                case 6:
                    viewAllData();
                    break;
                case 7:
                    demoAllOperations();
                    break;
                case 8:
                    System.out.println("\nThank you for using FoodSprint!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
    
    private static void userManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║              USER MANAGEMENT                     ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ 1. Add New User                                  ║");
            System.out.println("║ 2. View User by ID                               ║");
            System.out.println("║ 3. View User by Username                         ║");
            System.out.println("║ 4. View User by Email                            ║");
            System.out.println("║ 5. Update User                                   ║");
            System.out.println("║ 6. Delete User                                   ║");
            System.out.println("║ 7. View All Users                                ║");
            System.out.println("║ 8. Validate User Login                           ║");
            System.out.println("║ 9. Back to Main Menu                             ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice (1-9): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    getUserById();
                    break;
                case 3:
                    getUserByUsername();
                    break;
                case 4:
                    getUserByEmail();
                    break;
                case 5:
                    updateUser();
                    break;
                case 6:
                    deleteUser();
                    break;
                case 7:
                    viewAllUsers();
                    break;
                case 8:
                    validateUser();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
    
    private static void addUser() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("           ADD NEW USER");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter Role (CUSTOMER/ADMIN/DELIVERY_AGENT/SUPER_ADMIN): ");
        String role = scanner.nextLine();
        
        User user = new User(username, password, email, phone, address, role);
        boolean success = userDAO.addUser(user);
        
        if (success) {
            System.out.println("✅ User added successfully!");
        } else {
            System.out.println("❌ Failed to add user!");
        }
    }
    
    private static void getUserById() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        User user = userDAO.getUser(userId);
        if (user != null) {
            displayUserDetails(user);
        } else {
            System.out.println("❌ User not found with ID: " + userId);
        }
    }
    
    private static void getUserByUsername() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            displayUserDetails(user);
        } else {
            System.out.println("❌ User not found with username: " + username);
        }
    }
    
    private static void getUserByEmail() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            displayUserDetails(user);
        } else {
            System.out.println("❌ User not found with email: " + email);
        }
    }
    
    private static void displayUserDetails(User user) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("           USER DETAILS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("ID: " + user.getUserId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Role: " + user.getRole());
        System.out.println("Created: " + user.getCreatedDate());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
    
    private static void updateUser() {
        System.out.print("Enter User ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        User user = userDAO.getUser(userId);
        if (user == null) {
            System.out.println("❌ User not found!");
            return;
        }
        
        System.out.println("Current details:");
        displayUserDetails(user);
        
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Username [" + user.getUsername() + "]: ");
        String username = scanner.nextLine();
        if (!username.isEmpty()) user.setUsername(username);
        
        System.out.print("Password [" + user.getPassword() + "]: ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) user.setPassword(password);
        
        System.out.print("Email [" + user.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) user.setEmail(email);
        
        System.out.print("Phone [" + user.getPhone() + "]: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) user.setPhone(phone);
        
        System.out.print("Address [" + user.getAddress() + "]: ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) user.setAddress(address);
        
        System.out.print("Role [" + user.getRole() + "]: ");
        String role = scanner.nextLine();
        if (!role.isEmpty()) user.setRole(role);
        
        boolean success = userDAO.updateUser(user);
        if (success) {
            System.out.println("✅ User updated successfully!");
        } else {
            System.out.println("❌ Failed to update user!");
        }
    }
    
    private static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Are you sure you want to delete user ID " + userId + "? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            boolean success = userDAO.deleteUser(userId);
            if (success) {
                System.out.println("✅ User deleted successfully!");
            } else {
                System.out.println("❌ Failed to delete user!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void viewAllUsers() {
        List<User> users = userDAO.getAllUsers();
        
        if (users.isEmpty()) {
            System.out.println("No users found!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("           ALL USERS (" + users.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (User user : users) {
            System.out.println("ID: " + user.getUserId() + " | Username: " + user.getUsername() + 
                             " | Email: " + user.getEmail() + " | Role: " + user.getRole());
        }
    }
    
    private static void validateUser() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        boolean isValid = userDAO.validateUser(username, password);
        if (isValid) {
            System.out.println("✅ Login successful!");
        } else {
            System.out.println("❌ Invalid username or password!");
        }
    }
    
    private static void restaurantManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║           RESTAURANT MANAGEMENT                 ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ 1. Add New Restaurant                           ║");
            System.out.println("║ 2. View Restaurant by ID                        ║");
            System.out.println("║ 3. Update Restaurant                            ║");
            System.out.println("║ 4. Delete Restaurant                            ║");
            System.out.println("║ 5. View All Restaurants                         ║");
            System.out.println("║ 6. View Active Restaurants                      ║");
            System.out.println("║ 7. View Restaurants by Admin                    ║");
            System.out.println("║ 8. Back to Main Menu                            ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice (1-8): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addRestaurant();
                    break;
                case 2:
                    getRestaurantById();
                    break;
                case 3:
                    updateRestaurant();
                    break;
                case 4:
                    deleteRestaurant();
                    break;
                case 5:
                    viewAllRestaurants();
                    break;
                case 6:
                    viewActiveRestaurants();
                    break;
                case 7:
                    viewRestaurantsByAdmin();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
    
    private static void addRestaurant() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("         ADD NEW RESTAURANT");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        System.out.print("Enter Restaurant Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Customer Type: ");
        String customerType = scanner.nextLine();
        
        System.out.print("Enter Delivery Time (minutes): ");
        int deliveryTime = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter Admin User ID: ");
        int adminUserId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Rating (0-5): ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Is Active? (true/false): ");
        boolean isActive = scanner.nextBoolean();
        scanner.nextLine();
        
        System.out.print("Enter Image Path (optional): ");
        String imagePath = scanner.nextLine();
        
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setCustomerType(customerType);
        restaurant.setDeliveryTime(deliveryTime);
        restaurant.setAddress(address);
        restaurant.setAdminUserId(adminUserId);
        restaurant.setRating(rating);
        restaurant.setActive(isActive);
        restaurant.setImagePath(imagePath);
        
        boolean success = restaurantDAO.addRestaurant(restaurant);
        if (success) {
            System.out.println("✅ Restaurant added successfully!");
        } else {
            System.out.println("❌ Failed to add restaurant!");
        }
    }
    
    private static void getRestaurantById() {
        System.out.print("Enter Restaurant ID: ");
        int restaurantId = scanner.nextInt();
        scanner.nextLine();
        
        Restaurant restaurant = restaurantDAO.getRestaurant(restaurantId);
        if (restaurant != null) {
            displayRestaurantDetails(restaurant);
        } else {
            System.out.println("❌ Restaurant not found!");
        }
    }
    
    private static void displayRestaurantDetails(Restaurant restaurant) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("        RESTAURANT DETAILS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("ID: " + restaurant.getRestaurantId());
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Customer Type: " + restaurant.getCustomerType());
        System.out.println("Delivery Time: " + restaurant.getDeliveryTime() + " mins");
        System.out.println("Address: " + restaurant.getAddress());
        System.out.println("Admin User ID: " + restaurant.getAdminUserId());
        System.out.println("Rating: " + restaurant.getRating());
        System.out.println("Active: " + (restaurant.isActive() ? "Yes" : "No"));
        System.out.println("Image Path: " + restaurant.getImagePath());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
    
    private static void updateRestaurant() {
        System.out.print("Enter Restaurant ID to update: ");
        int restaurantId = scanner.nextInt();
        scanner.nextLine();
        
        Restaurant restaurant = restaurantDAO.getRestaurant(restaurantId);
        if (restaurant == null) {
            System.out.println("❌ Restaurant not found!");
            return;
        }
        
        System.out.println("Current details:");
        displayRestaurantDetails(restaurant);
        
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Name [" + restaurant.getName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) restaurant.setName(name);
        
        System.out.print("Customer Type [" + restaurant.getCustomerType() + "]: ");
        String customerType = scanner.nextLine();
        if (!customerType.isEmpty()) restaurant.setCustomerType(customerType);
        
        System.out.print("Delivery Time [" + restaurant.getDeliveryTime() + "]: ");
        String deliveryTimeStr = scanner.nextLine();
        if (!deliveryTimeStr.isEmpty()) restaurant.setDeliveryTime(Integer.parseInt(deliveryTimeStr));
        
        System.out.print("Address [" + restaurant.getAddress() + "]: ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) restaurant.setAddress(address);
        
        System.out.print("Admin User ID [" + restaurant.getAdminUserId() + "]: ");
        String adminIdStr = scanner.nextLine();
        if (!adminIdStr.isEmpty()) restaurant.setAdminUserId(Integer.parseInt(adminIdStr));
        
        System.out.print("Rating [" + restaurant.getRating() + "]: ");
        String ratingStr = scanner.nextLine();
        if (!ratingStr.isEmpty()) restaurant.setRating(Double.parseDouble(ratingStr));
        
        System.out.print("Active [" + restaurant.isActive() + "]: ");
        String activeStr = scanner.nextLine();
        if (!activeStr.isEmpty()) restaurant.setActive(Boolean.parseBoolean(activeStr));
        
        System.out.print("Image Path [" + restaurant.getImagePath() + "]: ");
        String imagePath = scanner.nextLine();
        if (!imagePath.isEmpty()) restaurant.setImagePath(imagePath);
        
        boolean success = restaurantDAO.updateRestaurant(restaurant);
        if (success) {
            System.out.println("✅ Restaurant updated successfully!");
        } else {
            System.out.println("❌ Failed to update restaurant!");
        }
    }
    
    private static void deleteRestaurant() {
        System.out.print("Enter Restaurant ID to delete: ");
        int restaurantId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            boolean success = restaurantDAO.deleteRestaurant(restaurantId);
            if (success) {
                System.out.println("✅ Restaurant deleted successfully!");
            } else {
                System.out.println("❌ Failed to delete restaurant!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void viewAllRestaurants() {
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
        
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("      ALL RESTAURANTS (" + restaurants.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Restaurant r : restaurants) {
            System.out.println("ID: " + r.getRestaurantId() + " | Name: " + r.getName() + 
                             " | Rating: " + r.getRating() + " | Active: " + (r.isActive() ? "Yes" : "No"));
        }
    }
    
    private static void viewActiveRestaurants() {
        List<Restaurant> restaurants = restaurantDAO.getActiveRestaurants();
        
        if (restaurants.isEmpty()) {
            System.out.println("No active restaurants found!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("      ACTIVE RESTAURANTS (" + restaurants.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Restaurant r : restaurants) {
            System.out.println("ID: " + r.getRestaurantId() + " | Name: " + r.getName() + 
                             " | Delivery: " + r.getDeliveryTime() + " mins | Rating: " + r.getRating());
        }
    }
    
    private static void viewRestaurantsByAdmin() {
        System.out.print("Enter Admin User ID: ");
        int adminId = scanner.nextInt();
        scanner.nextLine();
        
        List<Restaurant> restaurants = restaurantDAO.getRestaurantsByAdmin(adminId);
        
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found for this admin!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  RESTAURANTS MANAGED BY USER " + adminId);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Restaurant r : restaurants) {
            System.out.println("ID: " + r.getRestaurantId() + " | Name: " + r.getName());
        }
    }
    
    private static void menuManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║              MENU MANAGEMENT                     ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ 1. Add New Menu Item                            ║");
            System.out.println("║ 2. View Menu Item by ID                         ║");
            System.out.println("║ 3. Update Menu Item                             ║");
            System.out.println("║ 4. Delete Menu Item                             ║");
            System.out.println("║ 5. View All Menu Items                          ║");
            System.out.println("║ 6. View Menu by Restaurant                      ║");
            System.out.println("║ 7. View Available Menu by Restaurant            ║");
            System.out.println("║ 8. Back to Main Menu                            ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice (1-8): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    getMenuById();
                    break;
                case 3:
                    updateMenu();
                    break;
                case 4:
                    deleteMenu();
                    break;
                case 5:
                    viewAllMenu();
                    break;
                case 6:
                    getMenuByRestaurant();
                    break;
                case 7:
                    getAvailableMenuByRestaurant();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
    
    private static void addMenuItem() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("         ADD NEW MENU ITEM");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        System.out.print("Enter Restaurant ID: ");
        int restaurantId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Is Available? (true/false): ");
        boolean isAvailable = scanner.nextBoolean();
        scanner.nextLine();
        
        System.out.print("Enter Image Path (optional): ");
        String imagePath = scanner.nextLine();
        
        Menu menu = new Menu(restaurantId, itemName, description, price);
        menu.setAvailable(isAvailable);
        menu.setImagePath(imagePath);
        
        boolean success = menuDAO.addMenu(menu);
        if (success) {
            System.out.println("✅ Menu item added successfully!");
        } else {
            System.out.println("❌ Failed to add menu item!");
        }
    }
    
    private static void getMenuById() {
        System.out.print("Enter Menu ID: ");
        int menuId = scanner.nextInt();
        scanner.nextLine();
        
        Menu menu = menuDAO.getMenu(menuId);
        if (menu != null) {
            displayMenuDetails(menu);
        } else {
            System.out.println("❌ Menu item not found!");
        }
    }
    
    private static void displayMenuDetails(Menu menu) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("          MENU ITEM DETAILS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("ID: " + menu.getMenuId());
        System.out.println("Restaurant ID: " + menu.getRestaurantId());
        System.out.println("Item Name: " + menu.getItemName());
        System.out.println("Description: " + menu.getDescription());
        System.out.println("Price: $" + menu.getPrice());
        System.out.println("Available: " + (menu.isAvailable() ? "Yes" : "No"));
        System.out.println("Image Path: " + menu.getImagePath());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
    
    private static void updateMenu() {
        System.out.print("Enter Menu ID to update: ");
        int menuId = scanner.nextInt();
        scanner.nextLine();
        
        Menu menu = menuDAO.getMenu(menuId);
        if (menu == null) {
            System.out.println("❌ Menu item not found!");
            return;
        }
        
        System.out.println("Current details:");
        displayMenuDetails(menu);
        
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Restaurant ID [" + menu.getRestaurantId() + "]: ");
        String restIdStr = scanner.nextLine();
        if (!restIdStr.isEmpty()) menu.setRestaurantId(Integer.parseInt(restIdStr));
        
        System.out.print("Item Name [" + menu.getItemName() + "]: ");
        String itemName = scanner.nextLine();
        if (!itemName.isEmpty()) menu.setItemName(itemName);
        
        System.out.print("Description [" + menu.getDescription() + "]: ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) menu.setDescription(description);
        
        System.out.print("Price [" + menu.getPrice() + "]: ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isEmpty()) menu.setPrice(Double.parseDouble(priceStr));
        
        System.out.print("Available [" + menu.isAvailable() + "]: ");
        String availableStr = scanner.nextLine();
        if (!availableStr.isEmpty()) menu.setAvailable(Boolean.parseBoolean(availableStr));
        
        System.out.print("Image Path [" + menu.getImagePath() + "]: ");
        String imagePath = scanner.nextLine();
        if (!imagePath.isEmpty()) menu.setImagePath(imagePath);
        
        boolean success = menuDAO.updateMenu(menu);
        if (success) {
            System.out.println("✅ Menu item updated successfully!");
        } else {
            System.out.println("❌ Failed to update menu item!");
        }
    }
    
    private static void deleteMenu() {
        System.out.print("Enter Menu ID to delete: ");
        int menuId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            boolean success = menuDAO.deleteMenu(menuId);
            if (success) {
                System.out.println("✅ Menu item deleted successfully!");
            } else {
                System.out.println("❌ Failed to delete menu item!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void viewAllMenu() {
        List<Menu> menus = menuDAO.getAllMenu();
        
        if (menus.isEmpty()) {
            System.out.println("No menu items found!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("      ALL MENU ITEMS (" + menus.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Menu m : menus) {
            System.out.println("ID: " + m.getMenuId() + " | Item: " + m.getItemName() + 
                             " | Price: $" + m.getPrice() + " | Available: " + (m.isAvailable() ? "Yes" : "No"));
        }
    }
    
    private static void getMenuByRestaurant() {
        System.out.print("Enter Restaurant ID: ");
        int restaurantId = scanner.nextInt();
        scanner.nextLine();
        
        List<Menu> menus = menuDAO.getMenuByRestaurant(restaurantId);
        
        if (menus.isEmpty()) {
            System.out.println("No menu items found for this restaurant!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  MENU FOR RESTAURANT ID " + restaurantId);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Menu m : menus) {
            System.out.println("ID: " + m.getMenuId() + " | Item: " + m.getItemName() + 
                             " | Price: $" + m.getPrice());
        }
    }
    
    private static void getAvailableMenuByRestaurant() {
        System.out.print("Enter Restaurant ID: ");
        int restaurantId = scanner.nextInt();
        scanner.nextLine();
        
        List<Menu> menus = menuDAO.getAvailableMenuByRestaurant(restaurantId);
        
        if (menus.isEmpty()) {
            System.out.println("No available menu items found!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" AVAILABLE MENU FOR RESTAURANT ID " + restaurantId);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Menu m : menus) {
            System.out.println("ID: " + m.getMenuId() + " | Item: " + m.getItemName() + 
                             " | Price: $" + m.getPrice());
        }
    }
    
    private static void orderManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║              ORDER MANAGEMENT                    ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ 1. Create New Order                             ║");
            System.out.println("║ 2. View Order by ID                             ║");
            System.out.println("║ 3. Update Order                                 ║");
            System.out.println("║ 4. Delete Order                                 ║");
            System.out.println("║ 5. View All Orders                              ║");
            System.out.println("║ 6. View Orders by User                          ║");
            System.out.println("║ 7. View Orders by Restaurant                    ║");
            System.out.println("║ 8. Update Order Status                          ║");
            System.out.println("║ 9. Back to Main Menu                            ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice (1-9): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    getOrderById();
                    break;
                case 3:
                    updateOrder();
                    break;
                case 4:
                    deleteOrder();
                    break;
                case 5:
                    viewAllOrders();
                    break;
                case 6:
                    getOrdersByUser();
                    break;
                case 7:
                    getOrdersByRestaurant();
                    break;
                case 8:
                    updateOrderStatus();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
    
    private static void createOrder() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("          CREATE NEW ORDER");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        
        System.out.print("Enter Restaurant ID: ");
        int restaurantId = scanner.nextInt();
        
        System.out.print("Enter Total Amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter Payment Mode (CASH/CARD/UPI): ");
        String paymentMode = scanner.nextLine();
        
        Order order = new Order(userId, restaurantId, totalAmount);
        order.setPaymentMode(paymentMode);
        
        boolean success = orderDAO.addOrder(order);
        if (success) {
            System.out.println("✅ Order created successfully! Order ID: " + order.getOrderId());
            
            // Ask to add order items
            System.out.print("\nDo you want to add items to this order? (yes/no): ");
            String addItems = scanner.nextLine();
            
            if (addItems.equalsIgnoreCase("yes")) {
                addOrderItemsToOrder(order.getOrderId());
            }
        } else {
            System.out.println("❌ Failed to create order!");
        }
    }
    
    private static void addOrderItemsToOrder(int orderId) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("      ADD ITEMS TO ORDER #" + orderId);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        String continueAdding = "yes";
        while (continueAdding.equalsIgnoreCase("yes")) {
            System.out.print("\nEnter Menu ID: ");
            int menuId = scanner.nextInt();
            
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            
            System.out.print("Enter Price per item: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            
            OrderItem item = new OrderItem(orderId, menuId, quantity, price);
            boolean success = orderItemDAO.addOrderItem(item);
            
            if (success) {
                System.out.println("✅ Item added successfully!");
            } else {
                System.out.println("❌ Failed to add item!");
            }
            
            System.out.print("\nAdd another item? (yes/no): ");
            continueAdding = scanner.nextLine();
        }
    }
    
    private static void getOrderById() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        
        Order order = orderDAO.getOrder(orderId);
        if (order != null) {
            displayOrderDetails(order);
            
            // Show order items
            List<OrderItem> items = orderItemDAO.getOrderItemsByOrder(orderId);
            if (!items.isEmpty()) {
                System.out.println("\nOrder Items:");
                System.out.println("┌─────┬─────────┬──────────┬──────────┬──────────┐");
                System.out.println("│ ID  │ Menu ID │ Quantity │  Price   │ SubTotal │");
                System.out.println("├─────┼─────────┼──────────┼──────────┼──────────┤");
                for (OrderItem item : items) {
                    System.out.printf("│ %-3d │ %-7d │ %-8d │ $%-7.2f │ $%-7.2f │\n",
                            item.getOrderItemId(), item.getMenuId(), item.getQuantity(),
                            item.getPrice(), item.getSubTotal());
                }
                System.out.println("└─────┴─────────┴──────────┴──────────┴──────────┘");
            }
        } else {
            System.out.println("❌ Order not found!");
        }
    }
    
    private static void displayOrderDetails(Order order) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("           ORDER DETAILS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("User ID: " + order.getUserId());
        System.out.println("Restaurant ID: " + order.getRestaurantId());
        System.out.println("Order Date: " + order.getOrderDate());
        System.out.println("Total Amount: $" + order.getTotalAmount());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Payment Mode: " + order.getPaymentMode());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
    
    private static void updateOrder() {
        System.out.print("Enter Order ID to update: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        
        Order order = orderDAO.getOrder(orderId);
        if (order == null) {
            System.out.println("❌ Order not found!");
            return;
        }
        
        System.out.println("Current details:");
        displayOrderDetails(order);
        
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("User ID [" + order.getUserId() + "]: ");
        String userIdStr = scanner.nextLine();
        if (!userIdStr.isEmpty()) order.setUserId(Integer.parseInt(userIdStr));
        
        System.out.print("Restaurant ID [" + order.getRestaurantId() + "]: ");
        String restIdStr = scanner.nextLine();
        if (!restIdStr.isEmpty()) order.setRestaurantId(Integer.parseInt(restIdStr));
        
        System.out.print("Total Amount [" + order.getTotalAmount() + "]: ");
        String amountStr = scanner.nextLine();
        if (!amountStr.isEmpty()) order.setTotalAmount(Double.parseDouble(amountStr));
        
        System.out.print("Status [" + order.getStatus() + "]: ");
        String status = scanner.nextLine();
        if (!status.isEmpty()) order.setStatus(status);
        
        System.out.print("Payment Mode [" + order.getPaymentMode() + "]: ");
        String paymentMode = scanner.nextLine();
        if (!paymentMode.isEmpty()) order.setPaymentMode(paymentMode);
        
        boolean success = orderDAO.updateOrder(order);
        if (success) {
            System.out.println("✅ Order updated successfully!");
        } else {
            System.out.println("❌ Failed to update order!");
        }
    }
    
    private static void deleteOrder() {
        System.out.print("Enter Order ID to delete: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            boolean success = orderDAO.deleteOrder(orderId);
            if (success) {
                System.out.println("✅ Order deleted successfully!");
            } else {
                System.out.println("❌ Failed to delete order!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void viewAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        
        if (orders.isEmpty()) {
            System.out.println("No orders found!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("         ALL ORDERS (" + orders.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Order o : orders) {
            System.out.println("ID: " + o.getOrderId() + " | User: " + o.getUserId() + 
                             " | Restaurant: " + o.getRestaurantId() + " | Total: $" + o.getTotalAmount() + 
                             " | Status: " + o.getStatus());
        }
    }
    
    private static void getOrdersByUser() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        List<Order> orders = orderDAO.getOrdersByUser(userId);
        
        if (orders.isEmpty()) {
            System.out.println("No orders found for this user!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("    ORDERS FOR USER ID " + userId);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId() + " | Date: " + o.getOrderDate() + 
                             " | Total: $" + o.getTotalAmount() + " | Status: " + o.getStatus());
        }
    }
    
    private static void getOrdersByRestaurant() {
        System.out.print("Enter Restaurant ID: ");
        int restaurantId = scanner.nextInt();
        scanner.nextLine();
        
        List<Order> orders = orderDAO.getOrdersByRestaurant(restaurantId);
        
        if (orders.isEmpty()) {
            System.out.println("No orders found for this restaurant!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" ORDERS FOR RESTAURANT ID " + restaurantId);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId() + " | User: " + o.getUserId() + 
                             " | Total: $" + o.getTotalAmount() + " | Status: " + o.getStatus());
        }
    }
    
    private static void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter New Status (PENDING/CONFIRMED/PREPARING/DELIVERED/CANCELLED): ");
        String status = scanner.nextLine();
        
        boolean success = orderDAO.updateOrderStatus(orderId, status);
        if (success) {
            System.out.println("✅ Order status updated successfully!");
        } else {
            System.out.println("❌ Failed to update order status!");
        }
    }
    
    private static void orderItemManagementMenu() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║           ORDER ITEM MANAGEMENT                 ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║ 1. Add Order Item                               ║");
            System.out.println("║ 2. View Order Item by ID                        ║");
            System.out.println("║ 3. Update Order Item                            ║");
            System.out.println("║ 4. Delete Order Item                            ║");
            System.out.println("║ 5. View All Order Items                         ║");
            System.out.println("║ 6. View Order Items by Order                    ║");
            System.out.println("║ 7. Back to Main Menu                            ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice (1-7): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addOrderItem();
                    break;
                case 2:
                    getOrderItemById();
                    break;
                case 3:
                    updateOrderItem();
                    break;
                case 4:
                    deleteOrderItem();
                    break;
                case 5:
                    viewAllOrderItems();
                    break;
                case 6:
                    getOrderItemsByOrder();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
    
    private static void addOrderItem() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("         ADD ORDER ITEM");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        
        System.out.print("Enter Menu ID: ");
        int menuId = scanner.nextInt();
        
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        OrderItem item = new OrderItem(orderId, menuId, quantity, price);
        boolean success = orderItemDAO.addOrderItem(item);
        
        if (success) {
            System.out.println("✅ Order item added successfully!");
        } else {
            System.out.println("❌ Failed to add order item!");
        }
    }
    
    private static void getOrderItemById() {
        System.out.print("Enter Order Item ID: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();
        
        OrderItem item = orderItemDAO.getOrderItem(itemId);
        if (item != null) {
            displayOrderItemDetails(item);
        } else {
            System.out.println("❌ Order item not found!");
        }
    }
    
    private static void displayOrderItemDetails(OrderItem item) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("       ORDER ITEM DETAILS");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("ID: " + item.getOrderItemId());
        System.out.println("Order ID: " + item.getOrderId());
        System.out.println("Menu ID: " + item.getMenuId());
        System.out.println("Quantity: " + item.getQuantity());
        System.out.println("Price: $" + item.getPrice());
        System.out.println("Subtotal: $" + item.getSubTotal());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
    
    private static void updateOrderItem() {
        System.out.print("Enter Order Item ID to update: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();
        
        OrderItem item = orderItemDAO.getOrderItem(itemId);
        if (item == null) {
            System.out.println("❌ Order item not found!");
            return;
        }
        
        System.out.println("Current details:");
        displayOrderItemDetails(item);
        
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Order ID [" + item.getOrderId() + "]: ");
        String orderIdStr = scanner.nextLine();
        if (!orderIdStr.isEmpty()) item.setOrderId(Integer.parseInt(orderIdStr));
        
        System.out.print("Menu ID [" + item.getMenuId() + "]: ");
        String menuIdStr = scanner.nextLine();
        if (!menuIdStr.isEmpty()) item.setMenuId(Integer.parseInt(menuIdStr));
        
        System.out.print("Quantity [" + item.getQuantity() + "]: ");
        String quantityStr = scanner.nextLine();
        if (!quantityStr.isEmpty()) item.setQuantity(Integer.parseInt(quantityStr));
        
        System.out.print("Price [" + item.getPrice() + "]: ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isEmpty()) item.setPrice(Double.parseDouble(priceStr));
        
        boolean success = orderItemDAO.updateOrderItem(item);
        if (success) {
            System.out.println("✅ Order item updated successfully!");
        } else {
            System.out.println("❌ Failed to update order item!");
        }
    }
    
    private static void deleteOrderItem() {
        System.out.print("Enter Order Item ID to delete: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            boolean success = orderItemDAO.deleteOrderItem(itemId);
            if (success) {
                System.out.println("✅ Order item deleted successfully!");
            } else {
                System.out.println("❌ Failed to delete order item!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void viewAllOrderItems() {
        List<OrderItem> items = orderItemDAO.getAllOrderItems();
        
        if (items.isEmpty()) {
            System.out.println("No order items found!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("     ALL ORDER ITEMS (" + items.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        for (OrderItem item : items) {
            System.out.println("ID: " + item.getOrderItemId() + " | Order: " + item.getOrderId() + 
                             " | Menu: " + item.getMenuId() + " | Qty: " + item.getQuantity() + 
                             " | Total: $" + item.getSubTotal());
        }
    }
    
    private static void getOrderItemsByOrder() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        
        List<OrderItem> items = orderItemDAO.getOrderItemsByOrder(orderId);
        
        if (items.isEmpty()) {
            System.out.println("No items found for this order!");
            return;
        }
        
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  ITEMS FOR ORDER #" + orderId);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        double total = 0;
        for (OrderItem item : items) {
            System.out.println("Menu ID: " + item.getMenuId() + " | Quantity: " + item.getQuantity() + 
                             " | Price: $" + item.getPrice() + " | Subtotal: $" + item.getSubTotal());
            total += item.getSubTotal();
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Total Order Amount: $" + total);
    }
    
    private static void viewAllData() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║              VIEW ALL DATA                        ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        
        // Users
        List<User> users = userDAO.getAllUsers();
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("           USERS (" + users.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (User u : users) {
            System.out.println("ID: " + u.getUserId() + " | " + u.getUsername() + " | " + u.getEmail());
        }
        
        // Restaurants
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("       RESTAURANTS (" + restaurants.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (Restaurant r : restaurants) {
            System.out.println("ID: " + r.getRestaurantId() + " | " + r.getName() + " | Rating: " + r.getRating());
        }
        
        // Menu Items
        List<Menu> menus = menuDAO.getAllMenu();
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("       MENU ITEMS (" + menus.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (Menu m : menus) {
            System.out.println("ID: " + m.getMenuId() + " | " + m.getItemName() + " | $" + m.getPrice());
        }
        
        // Orders
        List<Order> orders = orderDAO.getAllOrders();
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("         ORDERS (" + orders.size() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (Order o : orders) {
            System.out.println("ID: " + o.getOrderId() + " | User: " + o.getUserId() + " | Total: $" + o.getTotalAmount());
        }
        
        System.out.println("\n✅ Data viewing complete!");
    }
    
    private static void demoAllOperations() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║           DEMO ALL OPERATIONS                    ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        
        try {
            System.out.println("\n1. Creating sample users...");
            User user1 = new User("demo_user", "demo123", "demo@example.com", "1234567890", "Demo Address", "CUSTOMER");
            userDAO.addUser(user1);
            System.out.println("   ✅ Sample user created");
            
            System.out.println("\n2. Creating sample restaurant...");
            Restaurant restaurant = new Restaurant("Demo Restaurant", "Demo Street", 1);
            restaurant.setCustomerType("ALL");
            restaurant.setDeliveryTime(25);
            restaurant.setRating(4.0);
            restaurantDAO.addRestaurant(restaurant);
            System.out.println("   ✅ Sample restaurant created");
            
            System.out.println("\n3. Creating sample menu items...");
            Menu menu1 = new Menu(1, "Demo Burger", "Delicious burger", 8.99);
            menuDAO.addMenu(menu1);
            
            Menu menu2 = new Menu(1, "Demo Pizza", "Cheesy pizza", 12.99);
            menuDAO.addMenu(menu2);
            System.out.println("   ✅ Sample menu items created");
            
            System.out.println("\n4. Creating sample order...");
            Order order = new Order(1, 1, 21.98);
            order.setPaymentMode("CASH");
            orderDAO.addOrder(order);
            System.out.println("   ✅ Sample order created (ID: " + order.getOrderId() + ")");
            
            System.out.println("\n5. Adding items to order...");
            OrderItem item1 = new OrderItem(order.getOrderId(), 1, 1, 8.99);
            orderItemDAO.addOrderItem(item1);
            
            OrderItem item2 = new OrderItem(order.getOrderId(), 2, 1, 12.99);
            orderItemDAO.addOrderItem(item2);
            System.out.println("   ✅ Sample order items added");
            
            System.out.println("\n6. Updating order status...");
            orderDAO.updateOrderStatus(order.getOrderId(), "DELIVERED");
            System.out.println("   ✅ Order status updated to DELIVERED");
            
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("          DEMO SUMMARY");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("• Users: " + userDAO.getAllUsers().size());
            System.out.println("• Restaurants: " + restaurantDAO.getAllRestaurants().size());
            System.out.println("• Menu Items: " + menuDAO.getAllMenu().size());
            System.out.println("• Orders: " + orderDAO.getAllOrders().size());
            System.out.println("• Order Items: " + orderItemDAO.getAllOrderItems().size());
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("\n✅ All operations demo completed successfully!");
            
        } catch (Exception e) {
            System.out.println("❌ Error during demo: " + e.getMessage());
        }
    }
}