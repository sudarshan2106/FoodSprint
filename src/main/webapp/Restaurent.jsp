<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.List,com.food.pojo.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodSprint | Premium Food Delivery</title>
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

        /* Header & Navigation */
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

        /* Main Navigation */
        .main-nav {
            display: flex;
            align-items: center;
            gap: 0.25rem;
            margin-left: 2rem;
        }

        .nav-link {
            display: flex;
            align-items: center;
            gap: 0.4rem;
            padding: 0.5rem 0.9rem;
            color: var(--secondary);
            text-decoration: none;
            font-size: 0.82rem;
            font-weight: 500;
            border-radius: var(--radius-full);
            transition: all 0.3s ease;
            white-space: nowrap;
        }

        .nav-link i {
            font-size: 0.85rem;
        }

        .nav-link:hover {
            background: var(--gray-light);
            color: var(--primary);
        }

        .nav-link.active {
            background: var(--primary);
            color: var(--white);
        }

        /* Search Bar */
        .search-container {
            flex: 1;
            max-width: 380px;
            margin: 0 1.5rem;
            position: relative;
        }

        .search-bar {
            width: 100%;
            padding: 0.55rem 1rem 0.55rem 2.5rem;
            background: var(--gray-light);
            border: 2px solid transparent;
            border-radius: var(--radius-full);
            color: var(--secondary);
            font-size: 0.82rem;
            outline: none;
            transition: all 0.3s ease;
        }

        .search-bar:focus {
            border-color: var(--primary);
            background: var(--white);
            box-shadow: 0 0 0 4px rgba(255, 107, 53, 0.1);
        }

        .search-bar::placeholder {
            color: var(--gray);
        }

        .search-icon {
            position: absolute;
            left: 0.9rem;
            top: 50%;
            transform: translateY(-50%);
            color: var(--gray);
            font-size: 0.8rem;
        }

        /* Nav Actions */
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

        .nav-btn-outline {
            background: transparent;
            color: var(--primary);
            border: 1.5px solid var(--primary);
        }

        .nav-btn-outline:hover {
            background: var(--primary);
            color: var(--white);
        }

        .nav-btn-primary {
            background: var(--gradient-primary);
            color: var(--white);
        }

        .nav-btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(255, 107, 53, 0.4);
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

        /* Hero Section */
        .hero {
            padding: 3rem 2rem 2rem;
            text-align: center;
            max-width: 800px;
            margin: 0 auto;
        }

        .hero-badge {
            display: inline-flex;
            align-items: center;
            gap: 0.4rem;
            padding: 0.4rem 0.9rem;
            background: rgba(255, 107, 53, 0.1);
            border-radius: var(--radius-full);
            font-size: 0.75rem;
            color: var(--primary);
            font-weight: 500;
            margin-bottom: 1.25rem;
        }

        .hero-badge i {
            font-size: 0.7rem;
        }

        .hero h1 {
            font-size: clamp(2rem, 5vw, 2.75rem);
            font-weight: 700;
            line-height: 1.2;
            margin-bottom: 1rem;
            color: var(--secondary);
        }

        .hero h1 span {
            color: var(--primary);
        }

        .hero p {
            color: var(--gray);
            font-size: 1rem;
            line-height: 1.6;
            margin-bottom: 2rem;
        }

        /* Filter Tags */
        .filter-container {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 0.5rem;
        }

        .filter-tag {
            padding: 0.45rem 1rem;
            background: var(--white);
            color: var(--secondary);
            border: none;
            border-radius: var(--radius-full);
            font-size: 0.8rem;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: var(--shadow-sm);
        }

        .filter-tag:hover {
            background: var(--primary-light);
            color: var(--white);
            transform: translateY(-2px);
        }

        .filter-tag.active {
            background: var(--primary);
            color: var(--white);
        }

        /* Food Categories Section */
        .food-categories {
            padding: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .categories-scroll {
            display: flex;
            gap: 1.25rem;
            overflow-x: auto;
            padding: 1rem 0;
            scrollbar-width: none;
        }

        .categories-scroll::-webkit-scrollbar {
            display: none;
        }

        .category-card {
            flex-shrink: 0;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .category-card:hover {
            transform: translateY(-5px);
        }

        .category-img {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid var(--white);
            box-shadow: var(--shadow-md);
            transition: all 0.3s ease;
        }

        .category-card:hover .category-img {
            border-color: var(--primary);
        }

        .category-name {
            margin-top: 0.5rem;
            font-size: 0.8rem;
            font-weight: 500;
            color: var(--secondary);
        }

        /* Stats Section */
        .stats {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 1rem;
            padding: 1.5rem;
            background: var(--white);
            border-radius: var(--radius-lg);
            margin: 0 2rem 2rem;
            max-width: 1000px;
            margin-left: auto;
            margin-right: auto;
            box-shadow: var(--shadow-sm);
        }

        .stat-item {
            text-align: center;
            padding: 0.75rem;
        }

        .stat-value {
            font-size: 1.75rem;
            font-weight: 700;
            color: var(--primary);
            margin-bottom: 0.25rem;
        }

        .stat-label {
            color: var(--gray);
            font-size: 0.8rem;
            font-weight: 500;
        }

        /* Main Content */
        main {
            padding: 1rem 2rem 2rem;
            max-width: 1400px;
            margin: 0 auto;
        }

        .section-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1.5rem;
        }

        .section-title {
            font-size: 1.5rem;
            font-weight: 600;
            color: var(--secondary);
        }

        .see-all {
            color: var(--primary);
            text-decoration: none;
            font-size: 0.85rem;
            font-weight: 500;
            display: flex;
            align-items: center;
            gap: 0.3rem;
        }

        .see-all:hover {
            text-decoration: underline;
        }

        /* Restaurant Grid */
        .restaurants-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 1.5rem;
        }

        /* Restaurant Card */
        .restaurant-card {
            background: var(--white);
            border-radius: var(--radius-lg);
            overflow: hidden;
            box-shadow: var(--shadow-sm);
            transition: all 0.3s ease;
        }

        .restaurant-card:hover {
            transform: translateY(-8px);
            box-shadow: var(--shadow-lg);
        }

        .card-image-container {
            height: 180px;
            overflow: hidden;
            position: relative;
        }

        .card-image {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.4s ease;
        }

        .restaurant-card:hover .card-image {
            transform: scale(1.08);
        }

        .card-badges {
            position: absolute;
            top: 0.75rem;
            left: 0.75rem;
            right: 0.75rem;
            display: flex;
            justify-content: space-between;
        }

        .badge {
            padding: 0.3rem 0.7rem;
            background: var(--white);
            color: var(--secondary);
            border-radius: var(--radius-full);
            font-size: 0.7rem;
            font-weight: 600;
            box-shadow: var(--shadow-sm);
        }

        .badge-featured {
            background: var(--primary);
            color: var(--white);
        }

        .badge-time {
            display: flex;
            align-items: center;
            gap: 0.25rem;
        }

        .favorite-btn {
            position: absolute;
            top: 0.75rem;
            right: 0.75rem;
            width: 32px;
            height: 32px;
            background: var(--white);
            border: none;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            box-shadow: var(--shadow-sm);
            transition: all 0.3s ease;
        }

        .favorite-btn i {
            color: var(--gray);
            font-size: 0.85rem;
            transition: all 0.3s ease;
        }

        .favorite-btn:hover i {
            color: #FF4757;
        }

        .favorite-btn.active i {
            color: #FF4757;
        }

        .card-content {
            padding: 1.25rem;
        }

        .card-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 0.5rem;
        }

        .restaurant-name {
            font-size: 1.1rem;
            font-weight: 600;
            color: var(--secondary);
            margin-bottom: 0.2rem;
        }

        .restaurant-cuisine {
            color: var(--gray);
            font-size: 0.8rem;
        }

        .rating {
            display: flex;
            align-items: center;
            gap: 0.25rem;
            background: #FFF8E7;
            padding: 0.35rem 0.6rem;
            border-radius: var(--radius-full);
        }

        .rating i {
            color: #FFB800;
            font-size: 0.75rem;
        }

        .rating-value {
            color: var(--secondary);
            font-weight: 600;
            font-size: 0.8rem;
        }

        .restaurant-info {
            display: flex;
            align-items: center;
            gap: 0.75rem;
            color: var(--gray);
            font-size: 0.8rem;
            margin-bottom: 1rem;
        }

        .restaurant-info span {
            display: flex;
            align-items: center;
            gap: 0.25rem;
        }

        .restaurant-info i {
            font-size: 0.75rem;
            color: var(--primary);
        }

        .card-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding-top: 1rem;
            border-top: 1px solid var(--gray-light);
        }

        .price-delivery {
            display: flex;
            align-items: center;
            gap: 0.75rem;
        }

        .price-range {
            font-size: 0.85rem;
            font-weight: 600;
            color: var(--accent);
        }

        .delivery-fee {
            font-size: 0.75rem;
            color: var(--gray);
        }

        .order-btn {
            padding: 0.5rem 1.25rem;
            background: var(--gradient-primary);
            color: var(--white);
            border: none;
            border-radius: var(--radius-full);
            font-weight: 600;
            font-size: 0.8rem;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .order-btn:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 15px rgba(255, 107, 53, 0.4);
        }

        /* Popular Foods Section */
        .popular-foods {
            margin-top: 3rem;
        }

        .foods-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
            gap: 1.25rem;
        }

        .food-card {
            background: var(--white);
            border-radius: var(--radius-lg);
            overflow: hidden;
            box-shadow: var(--shadow-sm);
            transition: all 0.3s ease;
        }

        .food-card:hover {
            transform: translateY(-5px);
            box-shadow: var(--shadow-md);
        }

        .food-image-container {
            height: 150px;
            overflow: hidden;
            position: relative;
        }

        .food-image {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.4s ease;
        }

        .food-card:hover .food-image {
            transform: scale(1.05);
        }

        .food-content {
            padding: 1rem;
        }

        .food-name {
            font-size: 0.95rem;
            font-weight: 600;
            color: var(--secondary);
            margin-bottom: 0.25rem;
        }

        .food-restaurant {
            font-size: 0.75rem;
            color: var(--gray);
            margin-bottom: 0.75rem;
        }

        .food-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .food-price {
            font-size: 1rem;
            font-weight: 700;
            color: var(--primary);
        }

        .add-btn {
            width: 32px;
            height: 32px;
            background: var(--gradient-primary);
            color: var(--white);
            border: none;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .add-btn:hover {
            transform: scale(1.1);
            box-shadow: 0 4px 12px rgba(255, 107, 53, 0.4);
        }

        /* CTA Section */
        .cta-section {
            padding: 4rem 2rem;
            text-align: center;
            background: var(--gradient-primary);
            margin: 3rem 0 0;
            position: relative;
            overflow: hidden;
        }

        .cta-section::before {
            content: '';
            position: absolute;
            top: -50%;
            right: -20%;
            width: 400px;
            height: 400px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 50%;
        }

        .cta-section::after {
            content: '';
            position: absolute;
            bottom: -30%;
            left: -10%;
            width: 300px;
            height: 300px;
            background: rgba(255, 255, 255, 0.08);
            border-radius: 50%;
        }

        .cta-content {
            position: relative;
            z-index: 1;
        }

        .cta-title {
            font-size: clamp(1.5rem, 3vw, 2rem);
            font-weight: 700;
            color: var(--white);
            margin-bottom: 0.75rem;
        }

        .cta-subtitle {
            font-size: 0.95rem;
            color: rgba(255, 255, 255, 0.9);
            margin-bottom: 1.5rem;
            max-width: 450px;
            margin-left: auto;
            margin-right: auto;
        }

        .cta-btn {
            padding: 0.85rem 2rem;
            background: var(--white);
            color: var(--primary);
            border: none;
            border-radius: var(--radius-full);
            font-size: 0.9rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .cta-btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
        }

        /* Footer */
        footer {
            padding: 3rem 2rem 1.5rem;
            background: var(--secondary);
            color: var(--white);
        }

        .footer-content {
            max-width: 1200px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 2rem;
            margin-bottom: 2rem;
        }

        .footer-brand {
            display: flex;
            align-items: center;
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

        .footer-desc {
            color: var(--gray);
            font-size: 0.85rem;
            line-height: 1.6;
        }

        .footer-title {
            font-size: 0.95rem;
            font-weight: 600;
            margin-bottom: 1rem;
            color: var(--white);
        }

        .footer-links {
            list-style: none;
        }

        .footer-links li {
            margin-bottom: 0.5rem;
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

        .social-links {
            display: flex;
            gap: 0.75rem;
        }

        .social-link {
            width: 36px;
            height: 36px;
            background: var(--dark-light);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: var(--white);
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .social-link:hover {
            background: var(--primary);
            transform: translateY(-3px);
        }

        .footer-bottom {
            text-align: center;
            padding-top: 1.5rem;
            border-top: 1px solid var(--dark-light);
        }

        .footer-bottom p {
            color: var(--gray);
            font-size: 0.8rem;
        }

        /* Responsive Design */
        @media (max-width: 1024px) {
            .restaurants-grid {
                grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            }

            .stats {
                grid-template-columns: repeat(2, 1fr);
            }

            .main-nav {
                display: none;
            }
        }

        @media (max-width: 768px) {
            header {
                padding: 0.75rem 1rem;
                flex-wrap: wrap;
                gap: 0.75rem;
            }

            .main-nav {
                display: none;
            }

            .search-container {
                order: 3;
                margin: 0;
                max-width: 100%;
                flex: 1 1 100%;
            }

            .nav-actions {
                gap: 0.3rem;
            }

            .nav-btn {
                padding: 0.4rem 0.6rem;
                font-size: 0.72rem;
            }

            .nav-btn .btn-text {
                display: none;
            }

            .hero {
                padding: 2rem 1rem 1.5rem;
            }

            .hero h1 {
                font-size: 1.75rem;
            }

            .hero p {
                font-size: 0.9rem;
            }

            .stats {
                grid-template-columns: repeat(2, 1fr);
                margin: 0 1rem 1.5rem;
                padding: 1rem;
            }

            .stat-value {
                font-size: 1.5rem;
            }

            main {
                padding: 1rem;
            }

            .restaurants-grid {
                grid-template-columns: 1fr;
                gap: 1rem;
            }

            .foods-grid {
                grid-template-columns: repeat(2, 1fr);
                gap: 1rem;
            }

            .cta-section {
                padding: 3rem 1.5rem;
            }

            .footer-content {
                grid-template-columns: 1fr;
                text-align: center;
            }

            .social-links {
                justify-content: center;
            }
        }

        @media (max-width: 480px) {
            .foods-grid {
                grid-template-columns: 1fr;
            }

            .filter-container {
                justify-content: flex-start;
                overflow-x: auto;
                flex-wrap: nowrap;
                padding-bottom: 0.5rem;
            }

            .filter-tag {
                flex-shrink: 0;
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
        <div class="logo">
            <div class="logo-icon">🍽️</div>
            <div class="logo-text">Food<span>Sprint</span></div>
        </div>

        <nav class="main-nav">
            <a href="#" class="nav-link active">
                <i class="fas fa-home"></i> Home
            </a>
            <a href="#" class="nav-link">
                <i class="fas fa-utensils"></i> Restaurants
            </a>
            <a href="#" class="nav-link">
                <i class="fas fa-tag"></i> Offers
            </a>
            <a href="#" class="nav-link">
                <i class="fas fa-headset"></i> Help
            </a>
        </nav>
        
        <div class="search-container">
            <input type="text" class="search-bar" placeholder="Search food or restaurants...">
            <i class="fas fa-search search-icon"></i>
        </div>
        
        <div class="nav-actions">
            <a href="#" class="nav-btn nav-btn-ghost">
                <i class="fas fa-map-marker-alt"></i>
                <span class="btn-text">Location</span>
            </a>
            <a href="Cart.jsp" class="nav-btn nav-btn-ghost">
                <i class="fas fa-shopping-cart"></i>
                <span class="btn-text">Cart</span>
                <span class="cart-badge">3</span>
            </a>
            <a href="#" class="nav-btn nav-btn-ghost">
                <i class="fas fa-user-circle"></i>
                <span class="btn-text">Profile</span>
            </a>
            <a href="Registration.html" class="nav-btn nav-btn-outline">
                <i class="fas fa-sign-in-alt"></i>
                <span class="btn-text">Login</span>
            </a>
        </div>
    </header>

    <!-- Mobile Bottom Navigation -->
    <div class="mobile-nav">
        <div class="mobile-nav-items">
            <a href="#" class="mobile-nav-item active">
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
            <a href="#" class="mobile-nav-item">
                <i class="fas fa-user"></i>
                <span>Profile</span>
            </a>
        </div>
    </div>

    <section class="hero">
        <div class="hero-badge">
            <i class="fas fa-bolt"></i>
            <span>Free delivery on first order</span>
        </div>
        <h1>Delicious Food <span>Delivered</span> to Your Door</h1>
        <p>Order from your favorite restaurants and get it delivered fast. Fresh, hot, and always on time.</p>
        
        <div class="filter-container">
            <button class="filter-tag active">All</button>
            <button class="filter-tag">🍕 Pizza</button>
            <button class="filter-tag">🍔 Burgers</button>
            <button class="filter-tag">🍣 Sushi</button>
            <button class="filter-tag">🍛 Indian</button>
            <button class="filter-tag">🌮 Mexican</button>
            <button class="filter-tag">🍜 Chinese</button>
            <button class="filter-tag">🥗 Healthy</button>
        </div>
    </section>

    <!-- Food Categories -->
    <section class="food-categories">
        <div class="categories-scroll">
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=200&h=200&fit=crop" alt="Pizza" class="category-img">
                <p class="category-name">Pizza</p>
            </div>
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=200&h=200&fit=crop" alt="Burgers" class="category-img">
                <p class="category-name">Burgers</p>
            </div>
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=200&h=200&fit=crop" alt="Sushi" class="category-img">
                <p class="category-name">Sushi</p>
            </div>
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=200&h=200&fit=crop" alt="Indian" class="category-img">
                <p class="category-name">Indian</p>
            </div>
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=200&h=200&fit=crop" alt="Mexican" class="category-img">
                <p class="category-name">Mexican</p>
            </div>
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1555126634-323283e090fa?w=200&h=200&fit=crop" alt="Chinese" class="category-img">
                <p class="category-name">Chinese</p>
            </div>
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=200&h=200&fit=crop" alt="Salads" class="category-img">
                <p class="category-name">Salads</p>
            </div>
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1551024601-bec78aea704b?w=200&h=200&fit=crop" alt="Desserts" class="category-img">
                <p class="category-name">Desserts</p>
            </div>
            <div class="category-card">
                <img src="https://images.unsplash.com/photo-1544025162-d76694265947?w=200&h=200&fit=crop" alt="BBQ" class="category-img">
                <p class="category-name">BBQ</p>
            </div>
        </div>
    </section>

    <div class="stats">
        <div class="stat-item">
            <div class="stat-value">200+</div>
            <div class="stat-label">Restaurants</div>
        </div>
        <div class="stat-item">
            <div class="stat-value">15-30</div>
            <div class="stat-label">Min Delivery</div>
        </div>
        <div class="stat-item">
            <div class="stat-value">4.9</div>
            <div class="stat-label">Avg Rating</div>
        </div>
        <div class="stat-item">
            <div class="stat-value">24/7</div>
            <div class="stat-label">Available</div>
        </div>
    </div>

    <main>
        <div class="section-header">
            <h2 class="section-title">Featured Restaurants</h2>
            <a href="#" class="see-all">See all <i class="fas fa-arrow-right"></i></a>
        </div>





        <div class="restaurants-grid">
        
        <%
        
           List<Restaurant> allRestaurants=(List<Restaurant>)request.getAttribute("allRestaurants");
        
        for(Restaurant restaurant:allRestaurants)   {
			%>
			
			
			
			
			<a href="Menu?restaurantId=<%= restaurant.getRestaurantId()%>"  style="text-decoration: none;">
			
			
			
			
			
			
			
			
			
			<div class="restaurant-card">
            <div class="card-image-container">
                <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %>" class="card-image">
                <div class="card-badges">
                    <span class="badge badge-featured"><i class="fas fa-star"></i> Featured</span>
                </div>
                <button class="favorite-btn">
                    <i class="far fa-heart"></i>
                </button>
            </div>
            <div class="card-content">
                <div class="card-header">
                    <div>
                        <h3 class="restaurant-name"><%= restaurant.getName() %></h3>
                        <p class="restaurant-cuisine"> <%= restaurant.getCustomerType() %></p>
                    </div>
                    <div class="rating">
                        <i class="fas fa-star"></i>
                        <span class="rating-value"><%= restaurant.getRating() %></span>
                    </div>
                </div>
                <div class="restaurant-info">
                    <span><i class="fas fa-clock"></i> <%= restaurant.getDeliveryTime() %> </span>
                    <span><i class="fas fa-map-marker-alt"></i> <%= restaurant.getAddress() %></span>
                </div>
               
            </div>
        </div>
			
			
			
			</a>
        	
        <%
		}
        
        
        %>
        
           
           
        </div>

        <!-- Popular Foods Section -->
        <section class="popular-foods">
            <div class="section-header">
                <h2 class="section-title">Popular Right Now</h2>
                <a href="#" class="see-all">See all <i class="fas fa-arrow-right"></i></a>
            </div>

            <div class="foods-grid">
                <div class="food-card">
                    <div class="food-image-container">
                        <img src="https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400&h=300&fit=crop" alt="Margherita Pizza" class="food-image">
                    </div>
                    <div class="food-content">
                        <h3 class="food-name">Margherita Pizza</h3>
                        <p class="food-restaurant">Bella Italia</p>
                        <div class="food-footer">
                            <span class="food-price">₹349</span>
                            <button class="add-btn"><i class="fas fa-plus"></i></button>
                        </div>
                    </div>
                </div>

                <div class="food-card">
                    <div class="food-image-container">
                        <img src="https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400&h=300&fit=crop" alt="Classic Burger" class="food-image">
                    </div>
                    <div class="food-content">
                        <h3 class="food-name">Classic Burger</h3>
                        <p class="food-restaurant">Burger House</p>
                        <div class="food-footer">
                            <span class="food-price">₹199</span>
                            <button class="add-btn"><i class="fas fa-plus"></i></button>
                        </div>
                    </div>
                </div>

                <div class="food-card">
                    <div class="food-image-container">
                        <img src="https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=400&h=300&fit=crop" alt="Salmon Sushi" class="food-image">
                    </div>
                    <div class="food-content">
                        <h3 class="food-name">Salmon Sushi Set</h3>
                        <p class="food-restaurant">Sakura Garden</p>
                        <div class="food-footer">
                            <span class="food-price">₹599</span>
                            <button class="add-btn"><i class="fas fa-plus"></i></button>
                        </div>
                    </div>
                </div>

                <div class="food-card">
                    <div class="food-image-container">
                        <img src="https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=400&h=300&fit=crop" alt="Butter Chicken" class="food-image">
                    </div>
                    <div class="food-content">
                        <h3 class="food-name">Butter Chicken</h3>
                        <p class="food-restaurant">Spice Kingdom</p>
                        <div class="food-footer">
                            <span class="food-price">₹299</span>
                            <button class="add-btn"><i class="fas fa-plus"></i></button>
                        </div>
                    </div>
                </div>

                <div class="food-card">
                    <div class="food-image-container">
                        <img src="https://images.unsplash.com/photo-1551024601-bec78aea704b?w=400&h=300&fit=crop" alt="Chocolate Cake" class="food-image">
                    </div>
                    <div class="food-content">
                        <h3 class="food-name">Chocolate Cake</h3>
                        <p class="food-restaurant">Sweet Dreams</p>
                        <div class="food-footer">
                            <span class="food-price">₹149</span>
                            <button class="add-btn"><i class="fas fa-plus"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

   
    
    <!-- Footer -->
    <footer>
        <div class="footer-content">
            <div>
                <div class="footer-brand">
                    <div class="logo-icon">🍽️</div>
                    <div class="logo-text">Food<span>Sprint</span></div>
                </div>
                <p class="footer-desc">Delicious food delivered fast to your doorstep. Order from the best restaurants in your city.</p>
            </div>
            <div>
                <h4 class="footer-title">Company</h4>
                <ul class="footer-links">
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Careers</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">Press</a></li>
                </ul>
            </div>
            <div>
                <h4 class="footer-title">Support</h4>
                <ul class="footer-links">
                    <li><a href="#">Help Center</a></li>
                    <li><a href="#">Safety</a></li>
                    <li><a href="#">Terms of Service</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                </ul>
            </div>
            <div>
                <h4 class="footer-title">Follow Us</h4>
                <div class="social-links">
                    <a href="#" class="social-link"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="social-link"><i class="fab fa-twitter"></i></a>
                    <a href="#" class="social-link"><i class="fab fa-instagram"></i></a>
                    <a href="#" class="social-link"><i class="fab fa-linkedin-in"></i></a>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <p>© 2024 FoodSprint. All rights reserved. Made with ❤️</p>
        </div>
    </footer>

    <script>
        // Favorite button toggle
        document.querySelectorAll('.favorite-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                this.classList.toggle('active');
                const icon = this.querySelector('i');
                if (this.classList.contains('active')) {
                    icon.classList.remove('far');
                    icon.classList.add('fas');
                } else {
                    icon.classList.remove('fas');
                    icon.classList.add('far');
                }
            });
        });

        // Filter tag active state
        document.querySelectorAll('.filter-tag').forEach(tag => {
            tag.addEventListener('click', function() {
                document.querySelectorAll('.filter-tag').forEach(t => t.classList.remove('active'));
                this.classList.add('active');
            });
        });

        // Mobile nav active state
        document.querySelectorAll('.mobile-nav-item').forEach(item => {
            item.addEventListener('click', function(e) {
                e.preventDefault();
                document.querySelectorAll('.mobile-nav-item').forEach(i => i.classList.remove('active'));
                this.classList.add('active');
            });
        });
    </script>
</body>
</html>