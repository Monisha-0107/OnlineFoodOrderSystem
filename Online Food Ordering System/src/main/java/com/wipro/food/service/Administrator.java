package com.wipro.food.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.wipro.food.bean.FoodOrderBean;
import com.wipro.food.dao.FoodOrderDAO;
import com.wipro.food.util.InvalidInputException;

public class Administrator {
	
	public String addRecord(FoodOrderBean bean){
		FoodOrderDAO dao = new FoodOrderDAO();
		    if(bean.getCustomerName()==null && bean.getOrderDate()==null){
		    	return "Invalid Input";
		    }
	       if(bean.getCustomerName().length()<2) {
		       return "INVALID CUSTOMER NAME";
	        }
	       if(bean.getQuantity()<1 || bean.getPrice()<=0) {
	    	   return "INVALID ORDER DETAILS";
	       }
	       if (dao.recordExists(bean.getCustomerName(), bean.getOrderDate())) {
	    	    return "ALREADY EXISTS";
	    	}
	       if (dao.recordExists(bean.getCustomerName(), bean.getOrderDate())) {
	            return "ALREADY EXISTS";
	        }
	       String orderId = dao.generateOrderID(bean.getCustomerName(),bean.getOrderDate());
	       bean.setOrderId(orderId);
	       String status = dao.createRecord(bean);
	       return status;
}
	public FoodOrderBean viewRecord(String customerName, Date orderDate) {
		FoodOrderDAO dao = new FoodOrderDAO();
		 FoodOrderBean bean = dao.fetchRecord(customerName,orderDate);
	       return bean;
	}
	public List<FoodOrderBean>viewAllRecords(){
		FoodOrderDAO dao = new FoodOrderDAO();
		List<FoodOrderBean>list=dao.fetchAllRecords();
		return list;
	}
	
	}
