package com.food.pojo;

import java.util.HashMap;
import java.util.Map;

import java.sql.Timestamp;

public class Cart {
	
	
	
	private Map<Integer,CartItem> items;
	
	public Cart() {
		this.items=new HashMap<>();
	}
	
	
	public void addItem(CartItem item) {
		int itemId=item.getItemId();
		if(items.containsKey(itemId)) {
			CartItem existingItem=items.get(itemId);
			int newQua=item.getQuantity();
			
			int oldQua=existingItem.getQuantity();
			
			int sumqua=newQua+ oldQua;
			
			existingItem.setQuantity(sumqua);
		}else {
			
			items.put(itemId,item);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		public void updateItem(int itemId,int quantity) {
			
			if(items.containsKey(itemId)) {
				if(quantity <= 0) {
					
					items.remove(itemId);
				}else {
					
					CartItem existingcartItem =items.get(itemId);
					existingcartItem.setQuantity(quantity);
					
				}
				
			}
		}
		
		public void removeItem(int itemId) {
			
			items.remove(itemId);
			
		}
		
		public Map<Integer,CartItem> getItems(){
			
			return items;
			
			
		}
		
		public double  getTotalPrice() {
			
			double totalPrice=0.0;
			for(CartItem item:items.values()) {
				
				totalPrice= totalPrice + item.getPrice()*item.getQuantity();
				
			}
			
			return totalPrice;
			
		}
		
		public void clear() {
			
			items.clear();
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

