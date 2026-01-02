<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.food.pojo.Menu"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodSprint | Menu - Bella Italia</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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
	min-height: 100vh;
	color: var(--secondary);
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

.header-left {
	display: flex;
	align-items: center;
	gap: 1rem;
}

.back-btn {
	width: 40px;
	height: 40px;
	border-radius: var(--radius-md);
	background: var(--gray-light);
	border: none;
	color: var(--secondary);
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 1rem;
	transition: all 0.3s ease;
	text-decoration: none;
}

.back-btn:hover {
	background: var(--primary);
	color: var(--white);
	transform: translateX(-3px);
}

.logo {
	display: flex;
	align-items: center;
	gap: 0.5rem;
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
}

.logo-text span {
	color: var(--primary);
}

/* Search Bar */
.search-container {
	flex: 1;
	max-width: 400px;
	margin: 0 1.5rem;
	position: relative;
}

.search-bar {
	width: 100%;
	padding: 0.6rem 1rem 0.6rem 2.5rem;
	background: var(--gray-light);
	border: 2px solid transparent;
	border-radius: var(--radius-full);
	color: var(--secondary);
	font-size: 0.85rem;
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
	font-size: 0.85rem;
}

/* Nav Actions */
.nav-actions {
	display: flex;
	align-items: center;
	gap: 0.5rem;
}

.nav-btn {
	display: flex;
	align-items: center;
	gap: 0.35rem;
	padding: 0.5rem 0.9rem;
	border-radius: var(--radius-full);
	font-weight: 500;
	font-size: 0.8rem;
	cursor: pointer;
	transition: all 0.3s ease;
	border: none;
	text-decoration: none;
}

.nav-btn i {
	font-size: 0.85rem;
}

.nav-btn-ghost {
	background: transparent;
	color: var(--secondary);
}

.nav-btn-ghost:hover {
	background: var(--gray-light);
	color: var(--primary);
}

.nav-btn-primary {
	background: var(--gradient-primary);
	color: var(--white);
	position: relative;
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
	padding: 0.1rem 0.4rem;
	border-radius: var(--radius-full);
	position: absolute;
	top: -5px;
	right: -5px;
}

/* Restaurant Hero */
.restaurant-hero {
	padding: 2rem;
	display: flex;
	align-items: center;
	gap: 1.5rem;
	background: var(--white);
	border-bottom: 1px solid rgba(0, 0, 0, 0.05);
}

.restaurant-logo-container {
	position: relative;
	flex-shrink: 0;
}

.restaurant-logo {
	width: 100px;
	height: 100px;
	border-radius: var(--radius-lg);
	object-fit: cover;
	border: 3px solid var(--gray-light);
	transition: all 0.3s ease;
}

.restaurant-logo:hover {
	transform: scale(1.05);
	border-color: var(--primary);
}

.restaurant-info h1 {
	font-size: 1.75rem;
	font-weight: 700;
	margin-bottom: 0.5rem;
	color: var(--secondary);
}

.restaurant-meta {
	display: flex;
	align-items: center;
	gap: 1rem;
	color: var(--gray);
	font-size: 0.85rem;
	flex-wrap: wrap;
}

.restaurant-meta span {
	display: flex;
	align-items: center;
	gap: 0.35rem;
	padding: 0.4rem 0.75rem;
	border-radius: var(--radius-full);
	background: var(--gray-light);
	transition: all 0.3s ease;
}

.restaurant-meta span:hover {
	background: var(--primary);
	color: var(--white);
	transform: translateY(-2px);
}

.restaurant-rating {
	background: #E8F5E9 !important;
	color: var(--accent) !important;
	font-weight: 600;
}

.restaurant-rating:hover {
	background: var(--accent) !important;
	color: var(--white) !important;
}

/* Category Tabs */
.category-tabs {
	padding: 1rem 2rem;
	display: flex;
	gap: 0.5rem;
	overflow-x: auto;
	position: sticky;
	top: 60px;
	z-index: 90;
	background: var(--white);
	border-bottom: 1px solid rgba(0, 0, 0, 0.05);
}

.category-tabs::-webkit-scrollbar {
	height: 0;
}

.category-tab {
	padding: 0.6rem 1.25rem;
	border-radius: var(--radius-full);
	background: var(--gray-light);
	color: var(--secondary);
	font-size: 0.8rem;
	font-weight: 500;
	cursor: pointer;
	transition: all 0.3s ease;
	border: none;
	white-space: nowrap;
	display: flex;
	align-items: center;
	gap: 0.5rem;
}

.category-tab:hover {
	background: var(--primary-light);
	color: var(--white);
	transform: translateY(-2px);
}

.category-tab.active {
	background: var(--primary);
	color: var(--white);
	box-shadow: 0 4px 12px rgba(255, 107, 53, 0.3);
}

/* Main Content */
main {
	padding: 2rem;
	max-width: 1400px;
	margin: 0 auto;
}

/* Section Header */
.section-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 1.5rem;
}

.section-title {
	font-size: 1.25rem;
	font-weight: 600;
	color: var(--secondary);
	display: flex;
	align-items: center;
	gap: 0.5rem;
}

.item-count {
	font-size: 0.85rem;
	color: var(--gray);
	font-weight: 400;
	background: var(--gray-light);
	padding: 0.25rem 0.75rem;
	border-radius: var(--radius-full);
}

/* Menu Grid */
.menu-grid {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
	gap: 1.5rem;
}

/* Menu Card */
.menu-card {
	background: var(--white);
	border-radius: var(--radius-lg);
	overflow: hidden;
	box-shadow: var(--shadow-sm);
	transition: all 0.3s ease;
}

.menu-card:hover {
	transform: translateY(-8px);
	box-shadow: var(--shadow-lg);
}

.menu-card-image {
	position: relative;
	height: 180px;
	overflow: hidden;
}

.menu-card-image img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	transition: transform 0.4s ease;
}

.menu-card:hover .menu-card-image img {
	transform: scale(1.08);
}

/* Badges */
.menu-card-badge {
	position: absolute;
	top: 0.75rem;
	left: 0.75rem;
	padding: 0.35rem 0.75rem;
	border-radius: var(--radius-full);
	font-size: 0.7rem;
	font-weight: 600;
	display: flex;
	align-items: center;
	gap: 0.25rem;
	box-shadow: var(--shadow-sm);
}

.badge-bestseller {
	background: linear-gradient(135deg, #FFD166 0%, #FF6B35 100%);
	color: var(--white);
}

.badge-new {
	background: linear-gradient(135deg, #06D6A0 0%, #00B4D8 100%);
	color: var(--white);
}

.badge-veg {
	background: #E8F5E9;
	color: #2E7D32;
}

.badge-spicy {
	background: #FFEBEE;
	color: #C62828;
}

/* Quick Add Button */
.quick-add {
	position: absolute;
	bottom: 0.75rem;
	right: 0.75rem;
	width: 40px;
	height: 40px;
	border-radius: 50%;
	background: var(--white);
	border: none;
	color: var(--primary);
	font-size: 1.25rem;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	opacity: 0;
	transform: scale(0.8);
	transition: all 0.3s ease;
	box-shadow: var(--shadow-md);
}

.menu-card:hover .quick-add {
	opacity: 1;
	transform: scale(1);
}

.quick-add:hover {
	background: var(--primary);
	color: var(--white);
	transform: scale(1.1);
}

/* Favorite Button */
.favorite-btn {
	position: absolute;
	top: 0.75rem;
	right: 0.75rem;
	width: 32px;
	height: 32px;
	border-radius: 50%;
	background: var(--white);
	border: none;
	color: var(--gray);
	font-size: 0.85rem;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	transition: all 0.3s ease;
	box-shadow: var(--shadow-sm);
}

.favorite-btn:hover, .favorite-btn.active {
	color: #FF4757;
}

.favorite-btn.active i {
	font-weight: 900;
}

.menu-card-content {
	padding: 1.25rem;
}

.menu-card-header {
	display: flex;
	justify-content: space-between;
	align-items: flex-start;
	margin-bottom: 0.5rem;
}

.menu-card-name {
	font-size: 1rem;
	font-weight: 600;
	color: var(--secondary);
	margin-bottom: 0.25rem;
}

.menu-card-rating {
	display: flex;
	align-items: center;
	gap: 0.25rem;
	background: #FFF8E7;
	padding: 0.3rem 0.6rem;
	border-radius: var(--radius-full);
}

.menu-card-rating i {
	color: #FFB800;
	font-size: 0.7rem;
}

.menu-card-rating .value {
	font-size: 0.75rem;
	font-weight: 600;
	color: var(--secondary);
}

.menu-card-description {
	color: var(--gray);
	font-size: 0.8rem;
	line-height: 1.5;
	margin-bottom: 1rem;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	overflow: hidden;
}

.menu-card-footer {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding-top: 1rem;
	border-top: 1px solid var(--gray-light);
}

.menu-card-price {
	font-size: 1.1rem;
	font-weight: 700;
	color: var(--primary);
}

.add-to-cart-btn {
	padding: 0.5rem 1rem;
	background: var(--gray-light);
	border: none;
	border-radius: var(--radius-full);
	color: var(--secondary);
	font-weight: 600;
	font-size: 0.8rem;
	cursor: pointer;
	transition: all 0.3s ease;
	display: flex;
	align-items: center;
	gap: 0.35rem;
}

.add-to-cart-btn:hover {
	background: var(--gradient-primary);
	color: var(--white);
	transform: scale(1.05);
	box-shadow: 0 4px 12px rgba(255, 107, 53, 0.3);
}

/* Footer */
footer {
	padding: 2rem;
	text-align: center;
	background: var(--secondary);
	color: var(--white);
	margin-top: 2rem;
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

.footer-text {
	color: var(--gray);
	font-size: 0.85rem;
}

/* Mobile Bottom Navigation */
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

.mobile-nav-item.active, .mobile-nav-item:hover {
	color: var(--primary);
}

/* Responsive */
@media ( max-width : 1024px) {
	.menu-grid {
		grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
	}
}

@media ( max-width : 768px) {
	header {
		padding: 0.75rem 1rem;
		flex-wrap: wrap;
		gap: 0.75rem;
	}
	.search-container {
		order: 3;
		margin: 0;
		max-width: 100%;
		flex: 1 1 100%;
	}
	.nav-actions .nav-btn span:not(.cart-badge) {
		display: none;
	}
	.restaurant-hero {
		padding: 1.5rem 1rem;
		flex-direction: column;
		text-align: center;
	}
	.restaurant-meta {
		justify-content: center;
	}
	.category-tabs {
		padding: 0.75rem 1rem;
		top: 55px;
	}
	main {
		padding: 1.5rem 1rem;
	}
	.menu-grid {
		grid-template-columns: 1fr;
		gap: 1rem;
	}
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
		<div class="header-left">
			<a href="index.jsp" class="back-btn"> <i
				class="fas fa-arrow-left"></i>
			</a>
			<div class="logo">
				<div class="logo-icon">🍽️</div>
				<div class="logo-text">
					Food<span>Sprint</span>
				</div>
			</div>
		</div>
		<div class="search-container">
			<input type="text" class="search-bar"
				placeholder="Search menu items..."> <i
				class="fas fa-search search-icon"></i>
		</div>
		<div class="nav-actions">
			<a href="#" class="nav-btn nav-btn-ghost"> <i
				class="fas fa-heart"></i> <span>Favorites</span>
			</a> <a href="Cart.jsp" class="nav-btn nav-btn-primary"> <i
				class="fas fa-shopping-cart"></i> <span>Cart</span> <span
				class="cart-badge">3</span>
			</a>
		</div>
	</header>

	<!-- Mobile Bottom Navigation -->
	<div class="mobile-nav">
		<div class="mobile-nav-items">
			<a href="Restaurent.jsp" class="mobile-nav-item"> <i
				class="fas fa-home"></i> <span>Home</span>
			</a> <a href="#" class="mobile-nav-item"> <i class="fas fa-search"></i>
				<span>Search</span>
			</a> <a href="cart.jsp" class="mobile-nav-item"> <i
				class="fas fa-shopping-cart"></i> <span>Cart</span>
			</a> <a href="#" class="mobile-nav-item"> <i class="fas fa-heart"></i>
				<span>Favorites</span>
			</a> <a href="#" class="mobile-nav-item"> <i class="fas fa-user"></i>
				<span>Profile</span>
			</a>
		</div>
	</div>

	<div class="restaurant-hero">
		<div class="restaurant-logo-container">
			<img
				src="https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=200&h=200&fit=crop"
				alt="Bella Italia" class="restaurant-logo">
		</div>
		<div class="restaurant-info">
			<h1>Bella Italia</h1>
			<div class="restaurant-meta">
				<span class="restaurant-rating"> <i class="fas fa-star"></i>
					4.9 (2,847)
				</span> <span><i class="fas fa-utensils"></i> Italian</span> <span><i
					class="fas fa-map-marker-alt"></i> 1.2 km</span> <span><i
					class="fas fa-clock"></i> 20-30 min</span> <span><i
					class="fas fa-truck"></i> Free delivery</span>
			</div>
		</div>
	</div>

	<div class="category-tabs">
		<button class="category-tab active">
			<i class="fas fa-th-large"></i> All Items
		</button>
		<button class="category-tab">
			<i class="fas fa-leaf"></i> Appetizers
		</button>
		<button class="category-tab">
			<i class="fas fa-bacon"></i> Pasta
		</button>
		<button class="category-tab">
			<i class="fas fa-pizza-slice"></i> Pizza
		</button>
		<button class="category-tab">
			<i class="fas fa-drumstick-bite"></i> Main Course
		</button>
		<button class="category-tab">
			<i class="fas fa-ice-cream"></i> Desserts
		</button>
		<button class="category-tab">
			<i class="fas fa-glass-martini-alt"></i> Drinks
		</button>
	</div>

	<main>
		<div class="menu-section">
			<div class="section-header">
				<h2 class="section-title">
					<i class="fas fa-fire" style="color: var(--primary);"></i> Popular
					Items <span class="item-count">12 items</span>
				</h2>
			</div>
			<div class="menu-grid">





				<%
                
                List <Menu> allMenuByRestaurant= (List <Menu>)request.getAttribute("allMenuByRestaurant");
                
                for(Menu menu :allMenuByRestaurant) {
            		
                	
                	
            		%>
				<div class="menu-card">
					<div class="menu-card-image">
						<img src="<%= menu.getImagePath() %>"
							alt=" <%= menu.getItemName() %>"> <span
							class="menu-card-badge badge-bestseller"> <i
							class="fas fa-fire"></i> Bestseller
						</span>
						<button class="favorite-btn">
							<i class="far fa-heart"></i>
						</button>
						<button class="quick-add">
							<i class="fas fa-plus"></i>
						</button>
					</div>
					<div class="menu-card-content">
						<div class="menu-card-header">
							<div>
								<h3 class="menu-card-name"><%= menu.getItemName() %></h3>
							</div>
							<div class="menu-card-rating">
								<i class="fas fa-star"></i> <span class="value">4.9</span>
							</div>
						</div>
						<p class="menu-card-description"><%= menu.getDescription() %></p>
						<div class="menu-card-footer">
							<span class="menu-card-price">Rs.<%= menu.getPrice() %></span>
							<form action="cart" class="add-to-cart-btn">
								<input type="hidden" name="itemId"
									value="<%= menu.getMenuId() %>"> <input type="hidden"
									name="restaurantId" value="<%= menu.getRestaurantId() %>">
								<input type="hidden" name="quantity" value="1"> <input
									type="hidden" name="action" value="add">
								<button type="submit">Add To Cart</button>
							</form>









						</div>
					</div>
				</div>



				<% }
                
                %>


			</div>
		</div>
	</main>

	<footer>
		<div class="footer-brand">
			<div class="logo-icon">🍽️</div>
			<div class="logo-text">
				Food<span>Sprint</span>
			</div>
		</div>
		<p class="footer-text">© 2024 FoodSprint. Delivering happiness to
			your doorstep. ❤️</p>
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

        // Category tab active state
        document.querySelectorAll('.category-tab').forEach(tab => {
            tab.addEventListener('click', function() {
                document.querySelectorAll('.category-tab').forEach(t => t.classList.remove('active'));
                this.classList.add('active');
            });
        });

        // Add to cart button animation
        document.querySelectorAll('.add-to-cart-btn, .quick-add').forEach(btn => {
            btn.addEventListener('click', function() {
                this.style.transform = 'scale(0.95)';
                setTimeout(() => {
                    this.style.transform = '';
                }, 150);
                
                // Update cart count
                const cartBadge = document.querySelector('.cart-badge');
                let count = parseInt(cartBadge.textContent);
                cartBadge.textContent = count + 1;
                cartBadge.style.transform = 'scale(1.3)';
                setTimeout(() => {
                    cartBadge.style.transform = '';
                }, 200);
            });
        });

        // Mobile nav active state
        document.querySelectorAll('.mobile-nav-item').forEach(item => {
            item.addEventListener('click', function(e) {
                document.querySelectorAll('.mobile-nav-item').forEach(i => i.classList.remove('active'));
                this.classList.add('active');
            });
        });
    </script>
</body>

</html>