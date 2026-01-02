package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.dao.impl.RestaurantDAOImpl;
import com.food.pojo.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/restaurent")
public class RestaurentServlet extends HttpServlet{

	protected  void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
		
	 
		RestaurantDAOImpl restaurantDAOImpl=new RestaurantDAOImpl();
		
		List<Restaurant>allRestaurants=restaurantDAOImpl.getAllRestaurants();
		for(Restaurant restaurant:allRestaurants)   {
			System.out.println(restaurant);
		}
		
		req.setAttribute("allRestaurants", allRestaurants);
		
		
		
		RequestDispatcher rd=req.getRequestDispatcher("Restaurent.jsp");
		
		rd.forward(req,resp);
		
		
}
}
