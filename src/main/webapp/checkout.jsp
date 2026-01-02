<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodSprint | Checkout</title>
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
            --primary-dark: #E85A2A;
            --secondary: #2B2D42;
            --accent: #06D6A0;
            --warning: #FFD166;
            --dark: #1A1A2E;
            --dark-light: #252540;
            --gray: #8D99AE;
            --gray-light: #EDF2F4;
            --white: #FFFFFF;
            --gradient-primary: linear-gradient(135deg, #FF6B35 0%, #FF8C5A 100%);
            --gradient-dark: linear-gradient(135deg, #1A1A2E 0%, #2B2D42 100%);
            --shadow-sm: 0 2px 8px rgba(0, 0, 0, 0.1);
            --shadow-md: 0 4px 16px rgba(0, 0, 0, 0.15);
            --shadow-lg: 0 8px 32px rgba(0, 0, 0, 0.2);
            --radius-sm: 8px;
            --radius-md: 12px;
            --radius-lg: 20px;
            --radius-full: 50px;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: var(--gray-light);
            color: var(--secondary);
            min-height: 100vh;
            overflow-x: hidden;
        }

        /* Header */
        header {
            padding: 0.75rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: var(--white);
            box-shadow: var(--shadow-sm);
            position: sticky;
            top: 0;
            z-index: 100;
        }

        .logo {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            flex-shrink: 0;
            text-decoration: none;
        }

        .logo-icon {
            width: 40px;
            height: 40px;
            background: var(--gradient-primary);
            border-radius: var(--radius-md);
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
            white-space: nowrap;
        }

        .logo-text span {
            color: var(--primary);
        }

        .nav-actions {
            display: flex;
            align-items: center;
            gap: 0.4rem;
            flex-shrink: 0;
        }

        .nav-btn {
            display: flex;
            align-items: center;
            gap: 0.3rem;
            padding: 0.45rem 0.8rem;
            border-radius: var(--radius-full);
            font-weight: 500;
            font-size: 0.78rem;
            cursor: pointer;
            transition: all 0.3s ease;
            border: none;
            text-decoration: none;
            white-space: nowrap;
        }

        .nav-btn i {
            font-size: 0.8rem;
        }

        .nav-btn-ghost {
            background: transparent;
            color: var(--secondary);
        }

        .nav-btn-ghost:hover {
            background: var(--gray-light);
            color: var(--primary);
        }

        .cart-badge {
            background: var(--accent);
            color: var(--white);
            font-size: 0.6rem;
            font-weight: 600;
            padding: 0.1rem 0.35rem;
            border-radius: var(--radius-full);
            margin-left: -0.15rem;
        }

        /* Main Content */
        main {
            padding: 2rem;
            max-width: 800px;
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
            margin-bottom: 0.5rem;
        }

        .page-title h1 span {
            color: var(--primary);
        }

        .page-title p {
            color: var(--gray);
            font-size: 0.95rem;
        }

        /* Checkout Progress */
        .checkout-progress {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 0.5rem;
            margin-bottom: 2.5rem;
            padding: 1.5rem;
            background: var(--white);
            border-radius: var(--radius-lg);
            box-shadow: var(--shadow-sm);
        }

        .progress-step {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .step-number {
            width: 35px;
            height: 35px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: 600;
            font-size: 0.85rem;
            background: var(--gray-light);
            color: var(--gray);
            transition: all 0.3s ease;
        }

        .step-number.active {
            background: var(--gradient-primary);
            color: var(--white);
        }

        .step-number.completed {
            background: var(--accent);
            color: var(--white);
        }

        .step-label {
            font-size: 0.85rem;
            font-weight: 500;
            color: var(--gray);
        }

        .step-label.active {
            color: var(--secondary);
        }

        .progress-line {
            width: 60px;
            height: 3px;
            background: var(--gray-light);
            border-radius: 2px;
        }

        .progress-line.completed {
            background: var(--accent);
        }

        /* Checkout Form */
        .checkout-form {
            display: flex;
            flex-direction: column;
            gap: 1.5rem;
        }

        .form-section {
            background: var(--white);
            border-radius: var(--radius-lg);
            padding: 1.75rem;
            box-shadow: var(--shadow-sm);
        }

        .section-header {
            display: flex;
            align-items: center;
            gap: 0.75rem;
            margin-bottom: 1.5rem;
            padding-bottom: 1rem;
            border-bottom: 2px solid var(--gray-light);
        }

        .section-icon {
            width: 45px;
            height: 45px;
            background: var(--gradient-primary);
            border-radius: var(--radius-md);
            display: flex;
            align-items: center;
            justify-content: center;
            color: var(--white);
            font-size: 1.1rem;
        }

        .section-title {
            font-size: 1.15rem;
            font-weight: 600;
            color: var(--secondary);
        }

        .section-subtitle {
            font-size: 0.8rem;
            color: var(--gray);
        }

        /* Form Groups */
        .form-row {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 1rem;
            margin-bottom: 1rem;
        }

        .form-row.single {
            grid-template-columns: 1fr;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .form-group label {
            font-size: 0.85rem;
            font-weight: 500;
            color: var(--secondary);
        }

        .form-group label .required {
            color: var(--primary);
        }

        .form-control {
            width: 100%;
            padding: 0.85rem 1rem;
            background: var(--gray-light);
            border: 2px solid transparent;
            border-radius: var(--radius-md);
            color: var(--secondary);
            font-family: 'Poppins', sans-serif;
            font-size: 0.9rem;
            outline: none;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: var(--primary);
            background: var(--white);
            box-shadow: 0 0 0 4px rgba(255, 107, 53, 0.1);
        }

        .form-control::placeholder {
            color: var(--gray);
        }

        .form-control.input-icon {
            padding-left: 2.75rem;
        }

        .input-wrapper {
            position: relative;
        }

        .input-wrapper i {
            position: absolute;
            left: 1rem;
            top: 50%;
            transform: translateY(-50%);
            color: var(--gray);
            font-size: 0.9rem;
        }

        .input-wrapper .form-control:focus + i,
        .input-wrapper:focus-within i {
            color: var(--primary);
        }

        textarea.form-control {
            min-height: 100px;
            resize: vertical;
        }

        /* Payment Methods */
        .payment-methods {
            display: flex;
            flex-direction: column;
            gap: 0.75rem;
        }

        .payment-option {
            display: flex;
            align-items: center;
            gap: 1rem;
            padding: 1rem 1.25rem;
            background: var(--gray-light);
            border: 2px solid transparent;
            border-radius: var(--radius-md);
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .payment-option:hover {
            background: var(--white);
            border-color: var(--primary-light);
        }

        .payment-option.selected {
            background: var(--white);
            border-color: var(--primary);
            box-shadow: 0 0 0 4px rgba(255, 107, 53, 0.1);
        }

        .payment-option input[type="radio"] {
            display: none;
        }

        .payment-radio {
            width: 22px;
            height: 22px;
            border: 2px solid var(--gray);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-shrink: 0;
            transition: all 0.3s ease;
        }

        .payment-option.selected .payment-radio {
            border-color: var(--primary);
        }

        .payment-radio::after {
            content: '';
            width: 10px;
            height: 10px;
            background: var(--primary);
            border-radius: 50%;
            transform: scale(0);
            transition: transform 0.3s ease;
        }

        .payment-option.selected .payment-radio::after {
            transform: scale(1);
        }

        .payment-icon {
            width: 50px;
            height: 35px;
            background: var(--white);
            border-radius: var(--radius-sm);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.25rem;
            box-shadow: var(--shadow-sm);
        }

        .payment-icon.card {
            background: linear-gradient(135deg, #1a1f71 0%, #2d47a0 100%);
            color: var(--white);
        }

        .payment-icon.upi {
            background: linear-gradient(135deg, #5f259f 0%, #8b45c6 100%);
            color: var(--white);
        }

        .payment-icon.wallet {
            background: linear-gradient(135deg, #00b9f1 0%, #0095c8 100%);
            color: var(--white);
        }

        .payment-icon.cod {
            background: linear-gradient(135deg, #06D6A0 0%, #05b88a 100%);
            color: var(--white);
        }

        .payment-icon.netbanking {
            background: linear-gradient(135deg, #FF6B35 0%, #FF8C5A 100%);
            color: var(--white);
        }

        .payment-details {
            flex: 1;
        }

        .payment-name {
            font-size: 0.95rem;
            font-weight: 600;
            color: var(--secondary);
        }

        .payment-desc {
            font-size: 0.75rem;
            color: var(--gray);
        }

        .payment-extra {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .payment-badge {
            padding: 0.2rem 0.5rem;
            background: rgba(6, 214, 160, 0.1);
            color: var(--accent);
            font-size: 0.65rem;
            font-weight: 600;
            border-radius: var(--radius-full);
        }

        /* Card Details Form */
        .card-details {
            display: none;
            margin-top: 1rem;
            padding: 1.25rem;
            background: var(--gray-light);
            border-radius: var(--radius-md);
            animation: slideDown 0.3s ease;
        }

        .card-details.show {
            display: block;
        }

        @keyframes slideDown {
            from {
                opacity: 0;
                transform: translateY(-10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* Checkout Button */
        .checkout-btn {
            width: 100%;
            padding: 1rem 2rem;
            background: var(--gradient-primary);
            color: var(--white);
            border: none;
            border-radius: var(--radius-md);
            font-family: 'Poppins', sans-serif;
            font-size: 1.1rem;
            font-weight: 600;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.75rem;
            transition: all 0.3s ease;
            text-decoration: none;
            margin-top: 1rem;
        }

        .checkout-btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 25px rgba(255, 107, 53, 0.4);
        }

        .checkout-btn i {
            font-size: 1.1rem;
        }

        /* Security Badge */
        .security-info {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.5rem;
            margin-top: 1rem;
            font-size: 0.8rem;
            color: var(--gray);
        }

        .security-info i {
            color: var(--accent);
        }

        /* Footer */
        footer {
            padding: 2rem;
            background: var(--secondary);
            color: var(--white);
            text-align: center;
            margin-top: 3rem;
        }

        .footer-content {
            max-width: 600px;
            margin: 0 auto;
        }

        .footer-brand {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.5rem;
            margin-bottom: 1rem;
        }

        .footer-brand .logo-icon {
            width: 36px;
            height: 36px;
            font-size: 1rem;
        }

        .footer-brand .logo-text {
            color: var(--white);
            font-size: 1.2rem;
        }

        .footer-links {
            display: flex;
            justify-content: center;
            gap: 1.5rem;
            margin-bottom: 1rem;
        }

        .footer-links a {
            color: var(--gray);
            text-decoration: none;
            font-size: 0.85rem;
            transition: color 0.3s ease;
        }

        .footer-links a:hover {
            color: var(--primary);
        }

        .footer-bottom p {
            color: var(--gray);
            font-size: 0.8rem;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            header {
                padding: 0.75rem 1rem;
            }

            main {
                padding: 1rem;
            }

            .page-title h1 {
                font-size: 1.5rem;
            }

            .checkout-progress {
                flex-wrap: wrap;
                gap: 1rem;
            }

            .progress-line {
                display: none;
            }

            .form-row {
                grid-template-columns: 1fr;
            }

            .form-section {
                padding: 1.25rem;
            }

            .payment-option {
                padding: 0.85rem 1rem;
            }

            .nav-btn .btn-text {
                display: none;
            }
        }

        @media (max-width: 480px) {
            .section-header {
                flex-direction: column;
                text-align: center;
            }

            .section-icon {
                margin-bottom: 0.5rem;
            }

            .checkout-progress {
                padding: 1rem;
            }

            .step-label {
                font-size: 0.75rem;
            }
        }

        /* Mobile Bottom Nav */
        .mobile-nav {
            display: none;
            position: fixed;
            bottom: 0;
            left: 0;
            right: 0;
            background: var(--white);
            padding: 0.5rem 1rem;
            box-shadow: 0 -4px 20px rgba(0, 0, 0, 0.1);
            z-index: 100;
        }

        .mobile-nav-items {
            display: flex;
            justify-content: space-around;
            align-items: center;
        }

        .mobile-nav-item {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 0.2rem;
            color: var(--gray);
            text-decoration: none;
            font-size: 0.65rem;
            font-weight: 500;
            padding: 0.5rem;
            transition: color 0.3s ease;
        }

        .mobile-nav-item i {
            font-size: 1.1rem;
        }

        .mobile-nav-item.active,
        .mobile-nav-item:hover {
            color: var(--primary);
        }

        @media (max-width: 768px) {
            .mobile-nav {
                display: block;
            }

            body {
                padding-bottom: 70px;
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
            <a href="Cart.jsp" class="nav-btn nav-btn-ghost">
                <i class="fas fa-arrow-left"></i>
                <span class="btn-text">Back to Cart</span>
            </a>
            <a href="#" class="nav-btn nav-btn-ghost">
                <i class="fas fa-user-circle"></i>
                <span class="btn-text">Profile</span>
            </a>
        </div>
    </header>

    <!-- Mobile Bottom Navigation -->
    <div class="mobile-nav">
        <div class="mobile-nav-items">
            <a href="index.jsp" class="mobile-nav-item">
                <i class="fas fa-home"></i>
                <span>Home</span>
            </a>
            <a href="#" class="mobile-nav-item">
                <i class="fas fa-search"></i>
                <span>Search</span>
            </a>
            <a href="Cart.jsp" class="mobile-nav-item">
                <i class="fas fa-shopping-cart"></i>
                <span>Cart</span>
            </a>
            <a href="#" class="mobile-nav-item">
                <i class="fas fa-heart"></i>
                <span>Favorites</span>
            </a>
            <a href="#" class="mobile-nav-item active">
                <i class="fas fa-credit-card"></i>
                <span>Checkout</span>
            </a>
        </div>
    </div>

    <main>
        <!-- Page Title -->
        <div class="page-title">
            <h1>Secure <span>Checkout</span></h1>
            <p>Complete your order by filling in the details below</p>
        </div>

        <!-- Checkout Progress -->
        <div class="checkout-progress">
            <div class="progress-step">
                <div class="step-number completed"><i class="fas fa-check"></i></div>
                <span class="step-label">Cart</span>
            </div>
            <div class="progress-line completed"></div>
            <div class="progress-step">
                <div class="step-number active">2</div>
                <span class="step-label active">Checkout</span>
            </div>
            <div class="progress-line"></div>
            <div class="progress-step">
                <div class="step-number">3</div>
                <span class="step-label">Confirmation</span>
            </div>
        </div>

        <!-- Checkout Form -->
        <div class="checkout-form">
            <!-- Personal Information Section -->
            <div class="form-section">
                <div class="section-header">
                    <div class="section-icon">
                        <i class="fas fa-user"></i>
                    </div>
                    <div>
                        <h3 class="section-title">Personal Information</h3>
                        <p class="section-subtitle">Please enter your contact details</p>
                    </div>
                </div>

                <form id="checkoutForm">
                    <div class="form-row">
                        <div class="form-group">
                            <label>Full Name <span class="required">*</span></label>
                            <div class="input-wrapper">
                                <input type="text" class="form-control input-icon" name="fullName" placeholder="Enter your full name" required>
                                <i class="fas fa-user"></i>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Email Address <span class="required">*</span></label>
                            <div class="input-wrapper">
                                <input type="email" class="form-control input-icon" name="email" placeholder="Enter email address" required>
                                <i class="fas fa-envelope"></i>
                            </div>
                        </div>
                    </div>

                    <div class="form-row single">
                        <div class="form-group">
                            <label>Phone Number <span class="required">*</span></label>
                            <div class="input-wrapper">
                                <input type="tel" class="form-control input-icon" name="phone" placeholder="Enter phone number" required>
                                <i class="fas fa-phone"></i>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Delivery Address Section -->
            <div class="form-section">
                <div class="section-header">
                    <div class="section-icon">
                        <i class="fas fa-map-marker-alt"></i>
                    </div>
                    <div>
                        <h3 class="section-title">Delivery Address</h3>
                        <p class="section-subtitle">Where should we deliver your order?</p>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>House / Flat No. <span class="required">*</span></label>
                        <div class="input-wrapper">
                            <input type="text" class="form-control input-icon" name="houseNo" placeholder="Enter house/flat number" required>
                            <i class="fas fa-home"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Street / Area <span class="required">*</span></label>
                        <div class="input-wrapper">
                            <input type="text" class="form-control input-icon" name="street" placeholder="Enter street/area" required>
                            <i class="fas fa-road"></i>
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>City <span class="required">*</span></label>
                        <div class="input-wrapper">
                            <input type="text" class="form-control input-icon" name="city" placeholder="Enter city" required>
                            <i class="fas fa-city"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>State <span class="required">*</span></label>
                        <div class="input-wrapper">
                            <input type="text" class="form-control input-icon" name="state" placeholder="Enter state" required>
                            <i class="fas fa-map"></i>
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>PIN Code <span class="required">*</span></label>
                        <div class="input-wrapper">
                            <input type="text" class="form-control input-icon" name="pincode" placeholder="Enter PIN code" required>
                            <i class="fas fa-map-pin"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Landmark (Optional)</label>
                        <div class="input-wrapper">
                            <input type="text" class="form-control input-icon" name="landmark" placeholder="Near landmark">
                            <i class="fas fa-location-arrow"></i>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Payment Method Section -->
            <div class="form-section">
                <div class="section-header">
                    <div class="section-icon">
                        <i class="fas fa-credit-card"></i>
                    </div>
                    <div>
                        <h3 class="section-title">Payment Method</h3>
                        <p class="section-subtitle">Choose your preferred payment option</p>
                    </div>
                </div>

                <div class="payment-methods">
                    <!-- Credit/Debit Card -->
                    <label class="payment-option" onclick="selectPayment(this, 'card')">
                        <input type="radio" name="payment" value="card">
                        <div class="payment-radio"></div>
                        <div class="payment-icon card">
                            <i class="fas fa-credit-card"></i>
                        </div>
                        <div class="payment-details">
                            <div class="payment-name">Credit / Debit Card</div>
                            <div class="payment-desc">Visa, Mastercard, Rupay & more</div>
                        </div>
                    </label>

                    <!-- Card Details Form -->
                    <div class="card-details" id="cardDetails">
                        <div class="form-row">
                            <div class="form-group" style="grid-column: span 2;">
                                <label>Card Number <span class="required">*</span></label>
                                <div class="input-wrapper">
                                    <input type="text" class="form-control input-icon" placeholder="1234 5678 9012 3456" maxlength="19">
                                    <i class="fas fa-credit-card"></i>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label>Expiry Date <span class="required">*</span></label>
                                <input type="text" class="form-control" placeholder="MM/YY" maxlength="5">
                            </div>
                            <div class="form-group">
                                <label>CVV <span class="required">*</span></label>
                                <div class="input-wrapper">
                                    <input type="password" class="form-control input-icon" placeholder="•••" maxlength="4">
                                    <i class="fas fa-lock"></i>
                                </div>
                            </div>
                        </div>
                        <div class="form-row single">
                            <div class="form-group">
                                <label>Cardholder Name <span class="required">*</span></label>
                                <input type="text" class="form-control" placeholder="Name on card">
                            </div>
                        </div>
                    </div>

                    <!-- UPI -->
                    <label class="payment-option selected" onclick="selectPayment(this, 'upi')">
                        <input type="radio" name="payment" value="upi" checked>
                        <div class="payment-radio"></div>
                        <div class="payment-icon upi">
                            <i class="fas fa-mobile-alt"></i>
                        </div>
                        <div class="payment-details">
                            <div class="payment-name">UPI</div>
                            <div class="payment-desc">Google Pay, PhonePe, Paytm & more</div>
                        </div>
                        <div class="payment-extra">
                            <span class="payment-badge">Popular</span>
                        </div>
                    </label>

                    <!-- Digital Wallet -->
                    <label class="payment-option" onclick="selectPayment(this, 'wallet')">
                        <input type="radio" name="payment" value="wallet">
                        <div class="payment-radio"></div>
                        <div class="payment-icon wallet">
                            <i class="fas fa-wallet"></i>
                        </div>
                        <div class="payment-details">
                            <div class="payment-name">Digital Wallet</div>
                            <div class="payment-desc">Paytm, Amazon Pay, Mobikwik</div>
                        </div>
                    </label>

                    <!-- Net Banking -->
                    <label class="payment-option" onclick="selectPayment(this, 'netbanking')">
                        <input type="radio" name="payment" value="netbanking">
                        <div class="payment-radio"></div>
                        <div class="payment-icon netbanking">
                            <i class="fas fa-university"></i>
                        </div>
                        <div class="payment-details">
                            <div class="payment-name">Net Banking</div>
                            <div class="payment-desc">All major banks supported</div>
                        </div>
                    </label>

                    <!-- Cash on Delivery -->
                    <label class="payment-option" onclick="selectPayment(this, 'cod')">
                        <input type="radio" name="payment" value="cod">
                        <div class="payment-radio"></div>
                        <div class="payment-icon cod">
                            <i class="fas fa-money-bill-wave"></i>
                        </div>
                        <div class="payment-details">
                            <div class="payment-name">Cash on Delivery</div>
                            <div class="payment-desc">Pay when your order arrives</div>
                        </div>
                    </label>
                </div>
            </div>

            <!-- Proceed to Checkout Button -->
            <a href="OrderConformation.html" class="checkout-btn">
                <i class="fas fa-lock"></i>
                <span>Proceed to Checkout</span>
                <i class="fas fa-arrow-right"></i>
            </a>

            <div class="security-info">
                <i class="fas fa-shield-alt"></i>
                <span>100% Secure Payment. Your data is protected.</span>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer>
        <div class="footer-content">
            <div class="footer-brand">
                <div class="logo-icon">🍽️</div>
                <div class="logo-text">Food<span>Sprint</span></div>
            </div>
            <div class="footer-links">
                <a href="#">About</a>
                <a href="#">Help</a>
                <a href="#">Terms</a>
                <a href="#">Privacy</a>
            </div>
            <div class="footer-bottom">
                <p>© 2024 FoodSprint. All rights reserved. Made with ❤️</p>
            </div>
        </div>
    </footer>

    <script>
        // Select Payment Method
        function selectPayment(element, method) {
            document.querySelectorAll('.payment-option').forEach(opt => {
                opt.classList.remove('selected');
            });
            element.classList.add('selected');
            
            // Show/hide card details
            const cardDetails = document.getElementById('cardDetails');
            if (method === 'card') {
                cardDetails.classList.add('show');
            } else {
                cardDetails.classList.remove('show');
            }
        }

        // Format Card Number
        document.addEventListener('DOMContentLoaded', function() {
            const cardInput = document.querySelector('input[placeholder="1234 5678 9012 3456"]');
            if (cardInput) {
                cardInput.addEventListener('input', function(e) {
                    let value = e.target.value.replace(/\s/g, '').replace(/\D/g, '');
                    let formattedValue = value.match(/.{1,4}/g)?.join(' ') || value;
                    e.target.value = formattedValue;
                });
            }

            // Format Expiry Date
            const expiryInput = document.querySelector('input[placeholder="MM/YY"]');
            if (expiryInput) {
                expiryInput.addEventListener('input', function(e) {
                    let value = e.target.value.replace(/\D/g, '');
                    if (value.length >= 2) {
                        value = value.substring(0, 2) + '/' + value.substring(2);
                    }
                    e.target.value = value;
                });
            }
        });

        // Mobile nav active state
        document.querySelectorAll('.mobile-nav-item').forEach(item => {
            item.addEventListener('click', function() {
                document.querySelectorAll('.mobile-nav-item').forEach(i => i.classList.remove('active'));
                this.classList.add('active');
            });
        });
    </script>
</body>
</html>