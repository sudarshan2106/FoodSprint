<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.food.pojo.Cart,com.food.pojo.CartItem" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodSprint | Your Cart</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        :root {
            --primary: #FF6B35;
            --primary-light: #FF8C5A;
            --secondary: #2B2D42;
            --accent: #06D6A0;
            --gray: #8D99AE;
            --gray-light: #F4F6F9;
            --white: #FFFFFF;
            --danger: #FF4757;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: var(--gray-light);
            color: var(--secondary);
            min-height: 100vh;
        }

        /* Header */
        header {
            padding: 1rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: var(--white);
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
            position: sticky;
            top: 0;
            z-index: 100;
        }

        .logo {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            text-decoration: none;
        }

        .logo-icon {
            width: 40px;
            height: 40px;
            background: linear-gradient(135deg, #FF6B35, #FF8C5A);
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.25rem;
            color: white;
        }

        .logo-text {
            font-size: 1.3rem;
            font-weight: 700;
            color: var(--secondary);
        }

        .logo-text span {
            color: var(--primary);
        }

        .nav-actions {
            display: flex;
            align-items: center;
            gap: 1rem;
        }

        .nav-btn {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            padding: 0.5rem 1rem;
            border-radius: 25px;
            font-weight: 500;
            font-size: 0.85rem;
            color: var(--secondary);
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .nav-btn:hover {
            background: var(--gray-light);
            color: var(--primary);
        }

        /* Main Content */
        main {
            padding: 2rem 1rem;
            max-width: 900px;
            margin: 0 auto;
        }

        /* Page Title */
        .page-title {
            text-align: center;
            margin-bottom: 2rem;
        }

        .page-title h1 {
            font-size: 2rem;
            font-weight: 700;
            color: var(--secondary);
        }

        .page-title h1 span {
            color: var(--primary);
        }

        .page-title p {
            color: var(--gray);
            font-size: 0.95rem;
            margin-top: 0.5rem;
        }

        /* Cart Card */
        .cart-card {
            background: var(--white);
            border-radius: 16px;
            padding: 1.5rem;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
            margin-bottom: 1.5rem;
        }

        /* Cart Item */
        .cart-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 1.25rem;
            background: var(--gray-light);
            border-radius: 12px;
            margin-bottom: 1rem;
            transition: all 0.3s ease;
        }

        .cart-item:last-child {
            margin-bottom: 0;
        }

        .cart-item:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        .item-info {
            display: flex;
            align-items: center;
            gap: 1rem;
            flex: 1;
        }

        .item-icon {
            width: 50px;
            height: 50px;
            background: linear-gradient(135deg, #FF6B35, #FF8C5A);
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5rem;
            color: var(--white);
            flex-shrink: 0;
        }

        .item-details h3 {
            font-size: 1rem;
            font-weight: 600;
            color: var(--secondary);
            margin-bottom: 0.25rem;
        }

        .item-price {
            font-size: 0.85rem;
            color: var(--gray);
        }

        .item-price strong {
            color: var(--primary);
            font-weight: 600;
        }

        /* Quantity Controls */
        .quantity-section {
            display: flex;
            align-items: center;
            gap: 1.5rem;
        }

        .quantity-controls {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            background: var(--white);
            padding: 0.4rem;
            border-radius: 25px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }

        .quantity-btn {
            width: 32px;
            height: 32px;
            border-radius: 50%;
            border: none;
            background: var(--gray-light);
            color: var(--secondary);
            font-weight: bold;
            font-size: 1rem;
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .quantity-btn:hover:not(:disabled) {
            background: var(--primary);
            color: var(--white);
        }

        .quantity-btn:disabled {
            opacity: 0.5;
            cursor: not-allowed;
        }

        .quantity-value {
            font-weight: 600;
            font-size: 1rem;
            min-width: 30px;
            text-align: center;
            color: var(--secondary);
        }

        .item-total {
            font-size: 1rem;
            font-weight: 700;
            color: var(--secondary);
            min-width: 80px;
            text-align: right;
        }

        /* Remove Button */
        .remove-btn {
            background: transparent;
            border: none;
            color: var(--danger);
            font-size: 1.1rem;
            cursor: pointer;
            padding: 0.5rem;
            border-radius: 8px;
            transition: all 0.3s ease;
        }

        .remove-btn:hover {
            background: rgba(255, 71, 87, 0.1);
            transform: scale(1.1);
        }

        /* Cart Summary */
        .cart-summary {
            background: var(--white);
            border-radius: 16px;
            padding: 1.5rem;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
            margin-bottom: 1.5rem;
        }

        .summary-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0.75rem 0;
            border-bottom: 1px dashed #E8E8E8;
        }

        .summary-row:last-child {
            border-bottom: none;
            padding-top: 1rem;
            margin-top: 0.5rem;
            border-top: 2px solid var(--gray-light);
        }

        .summary-label {
            font-size: 0.95rem;
            color: var(--gray);
        }

        .summary-value {
            font-size: 0.95rem;
            font-weight: 600;
            color: var(--secondary);
        }

        .summary-row.total .summary-label {
            font-size: 1.1rem;
            font-weight: 600;
            color: var(--secondary);
        }

        .summary-row.total .summary-value {
            font-size: 1.3rem;
            font-weight: 700;
            color: var(--primary);
        }

        /* Action Buttons */
        .action-buttons {
            display: flex;
            gap: 1rem;
            justify-content: center;
            flex-wrap: wrap;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            gap: 0.5rem;
            padding: 0.875rem 1.75rem;
            border-radius: 10px;
            font-family: 'Poppins', sans-serif;
            font-size: 0.9rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            border: none;
        }

        .btn-primary {
            background: linear-gradient(135deg, #FF6B35, #FF8C5A);
            color: var(--white);
            flex: 1;
            max-width: 300px;
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(255, 107, 53, 0.35);
        }

        .btn-outline {
            background: var(--white);
            color: var(--secondary);
            border: 2px solid #E8E8E8;
        }

        .btn-outline:hover {
            border-color: var(--primary);
            color: var(--primary);
        }

        /* Empty Cart */
        .empty-cart {
            text-align: center;
            padding: 3rem 1rem;
        }

        .empty-cart-icon {
            width: 100px;
            height: 100px;
            background: var(--gray-light);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1.5rem;
            font-size: 3rem;
        }

        .empty-cart h2 {
            font-size: 1.5rem;
            font-weight: 600;
            color: var(--secondary);
            margin-bottom: 0.5rem;
        }

        .empty-cart p {
            color: var(--gray);
            margin-bottom: 1.5rem;
        }

        /* Hidden form styling */
        .hidden-form {
            display: inline;
        }

        /* Responsive */
        @media (max-width: 768px) {
            main {
                padding: 1.5rem 1rem;
            }

            .cart-item {
                flex-direction: column;
                align-items: flex-start;
                gap: 1rem;
            }

            .quantity-section {
                width: 100%;
                justify-content: space-between;
            }

            .item-total {
                text-align: left;
            }

            .action-buttons {
                flex-direction: column;
            }

            .btn {
                width: 100%;
                max-width: none;
            }

            header {
                padding: 1rem;
            }

            .nav-btn span {
                display: none;
            }
        }

        @media (max-width: 480px) {
            .page-title h1 {
                font-size: 1.5rem;
            }

            .item-info {
                flex-direction: column;
                align-items: flex-start;
                gap: 0.5rem;
            }

            .item-icon {
                width: 40px;
                height: 40px;
                font-size: 1.2rem;
            }
        }
    </style>
</head>

<body>

    <header>
        <a href="index.jsp" class="logo">
            <div class="logo-icon">🍽️</div>
            <div class="logo-text">Food<span>Sprint</span></div>
        </a>
        
        <div class="nav-actions">
            <a href="restaurent" class="nav-btn">
                <i class="fas fa-home"></i>
                <span>Home</span>
            </a>
            <a href="restaurent" class="nav-btn">
                <i class="fas fa-utensils"></i>
                <span>Restaurants</span>
            </a>
            <a href="#" class="nav-btn">
                <i class="fas fa-user-circle"></i>
                <span>Profile</span>
            </a>
        </div>
    </header>

    <main>
        <!-- Page Title -->
        <div class="page-title">
            <h1>Your <span>Cart</span></h1>
            <p>Review your items before checkout</p>
        </div>

        <%
        Cart cart = (Cart) session.getAttribute("cart");
        Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");
        
        if(cart != null && !cart.getItems().isEmpty()) {
        %>

        <!-- Cart Items -->
        <div class="cart-card">
            <% for(CartItem item : cart.getItems().values()) { %>
            <div class="cart-item">
                <div class="item-info">
                    <div class="item-icon">
                        <i class="fas fa-hamburger"></i>
                    </div>
                    <div class="item-details">
                        <h3><%= item.getName() %></h3>
                        <p class="item-price">₹<%= item.getPrice() %> per item</p>
                    </div>
                </div>
                
                <div class="quantity-section">
                    <div class="quantity-controls">
                        <form action="cart" class="hidden-form">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                            <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                            <button type="submit" class="quantity-btn" <%= (item.getQuantity() == 1) ? "disabled" : "" %>>−</button>
                        </form>
                        
                        <span class="quantity-value"><%= item.getQuantity() %></span>
                        
                        <form action="cart" class="hidden-form">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                            <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                            <button type="submit" class="quantity-btn">+</button>
                        </form>
                    </div>
                    
                    <div class="item-total">₹<%= item.getTotalPrice() %></div>
                    
                    <form action="cart" class="hidden-form">
                        <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                        <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                        <input type="hidden" name="action" value="remove">
                        <button type="submit" class="remove-btn" title="Remove item">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                    </form>
                </div>
            </div>
            <% } %>
        </div>

        <!-- Cart Summary -->
        <div class="cart-summary">
            <div class="summary-row">
                <span class="summary-label">Subtotal</span>
                <span class="summary-value">₹<%= cart.getTotalPrice() %></span>
            </div>
            <div class="summary-row">
                <span class="summary-label">Delivery Fee</span>
                <span class="summary-value" style="color: var(--accent);">FREE</span>
            </div>
            <div class="summary-row total">
                <span class="summary-label">Grand Total</span>
                <span class="summary-value">₹<%= cart.getTotalPrice() %></span>
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="action-buttons">
            <a href="Menu?restaurantId=<%= session.getAttribute("oldRestaurantId") %>" class="btn btn-outline">
                <i class="fas fa-plus"></i>
                Add More Items
            </a>
            <form action="checkout.jsp" style="flex: 1; max-width: 300px;">
                <button type="submit" class="btn btn-primary" style="width: 100%;">
                    <i class="fas fa-lock"></i>
                    Proceed to Checkout
                </button>
            </form>
        </div>

        <% } else { %>

        <!-- Empty Cart -->
        <div class="cart-card">
            <div class="empty-cart">
                <div class="empty-cart-icon">🛒</div>
                <h2>Your Cart is Empty</h2>
                <p>Looks like you haven't added any items yet.</p>
                <a href="restaurent" class="btn btn-primary">
                    <i class="fas fa-utensils"></i>
                    Browse Restaurants
                </a>
            </div>
        </div>

        <% } %>
    </main>

</body>
</html>