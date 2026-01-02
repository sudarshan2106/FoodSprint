 package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.dao.impl.MenuDAOImpl;
import com.food.dao.impl.RestaurantDAOImpl;
import com.food.pojo.Menu;
import com.food.pojo.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Menu")
public class MenuServlet extends HttpServlet{

	protected  void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
		
		int restaurantId=Integer.parseInt(req.getParameter("restaurantId"));
		MenuDAOImpl menuDAOImpl=new MenuDAOImpl();
		List <Menu> allMenuByRestaurant=menuDAOImpl.getMenuByRestaurant(restaurantId);
		for(Menu menu :allMenuByRestaurant) {
		System.out.println(menu);
		}
		
		req.setAttribute("allMenuByRestaurant", allMenuByRestaurant);
		RequestDispatcher rd=req.getRequestDispatcher("Menu.jsp");
		rd.forward(req, resp);
		
        
	}
	}