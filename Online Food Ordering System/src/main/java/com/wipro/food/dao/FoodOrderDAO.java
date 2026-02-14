package com.wipro.food.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.wipro.food.bean.FoodOrderBean;
import com.wipro.food.util.DBUtil;

public class FoodOrderDAO {
		public int generateSequenceNumber() {
			   Connection connection = DBUtil.getDBConnection();
			   int seq = 0;
			   String query = "SELECT FOODORDER_SEQ.NEXTVAL FROM dual";
			   try {
			       PreparedStatement ps = connection.prepareStatement(query);
			       ResultSet rs = ps.executeQuery();
			       if (rs.next()) {
			           return rs.getInt(1);
			       }
			   } catch (SQLException e) {
		         e.printStackTrace();
			   }
			   return seq;
			}
	public String createRecord(FoodOrderBean bean) { 
		Connection connection =DBUtil.getDBConnection();
		String query="INSERT INTO FOODORDER_TB VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,bean.getOrderId());
			ps.setString(2,bean.getCustomerName());
			ps.setString(3,bean.getFoodItem());
			ps.setDate(4, bean.getOrderDate());
			ps.setInt(5,bean.getQuantity());
			ps.setDouble(6, bean.getPrice());
			ps.setString(7,bean.getRemarks());
			ps.executeUpdate();
			return bean.getOrderId();
			
		}catch(Exception e) {
        	e.printStackTrace();
        }
		return "fail";
		
	}
	public FoodOrderBean fetchRecord(String customerName , Date orderDate) {
		Connection connection =DBUtil.getDBConnection();
		String query="SELECT * FROM FOODORDER_TB WHERE CUSTOMERNAME=? AND ORDER_DATE=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,customerName);
			ps.setDate(2, orderDate);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				FoodOrderBean fb=new FoodOrderBean();
				fb.setOrderId(rs.getString(1));
				fb.setCustomerName(rs.getString(2));
				fb.setFoodItem(rs.getString(3));
				fb.setOrderDate(rs.getDate(4));
				fb.setQuantity(rs.getInt(5));
				fb.setPrice(rs.getDouble(6));
				fb.setRemarks(rs.getString(7));
				return fb;	
			}
			else {
				return null;
			}
			
		}
		catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
		

}

	public String generateOrderID(String customerName, Date orderDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd"); 
		String temp = f.format(orderDate); 
		String name=customerName.substring(0,2).toUpperCase();
		int seq = generateSequenceNumber();
		String seqPart=String.format("%02d",seq);
		String orderId=temp+name+seqPart;
		return orderId;
		
	}
	public boolean recordExists(String customerName, Date orderDate) {
		Connection connection =DBUtil.getDBConnection();
		String query="SELECT COUNT(*) FROM FOODORDER_TB WHERE CUSTOMERNAME=? AND ORDER_DATE=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,customerName);
			ps.setDate(2,orderDate);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)>0;
			}
			
		}
		catch(Exception e) {
        	e.printStackTrace();
        }
		return false;
		
	}
	public List<FoodOrderBean> fetchAllRecords()  {
		List<FoodOrderBean>list=new ArrayList<>();
		String query ="SELECT * FROM FOODORDER_TB ";
		try {
			Connection connection = DBUtil.getDBConnection();
	        PreparedStatement ps = connection.prepareStatement(query);
	        ResultSet rs= ps.executeQuery();
	        while(rs.next()) {
	        	FoodOrderBean fb=new FoodOrderBean();
	        	fb.setOrderId(rs.getString("ORDERID"));
	        	fb.setCustomerName(rs.getString("CUSTOMERNAME"));
	        	fb.setFoodItem(rs.getString("FOODITEM"));
	        	fb.setOrderDate(rs.getDate("ORDER_DATE"));
	        	fb.setQuantity(rs.getInt("QUANTITY"));
	        	fb.setPrice(rs.getDouble("PRICE"));
	        	fb.setRemarks(rs.getString("REMARKS"));
	        	list.add(fb);	
	        }
	       
		}
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        return list;
		}
	
	
}
