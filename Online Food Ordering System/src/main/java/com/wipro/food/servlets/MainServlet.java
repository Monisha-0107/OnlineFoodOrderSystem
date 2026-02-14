package com.wipro.food.servlets;

import com.wipro.food.bean.FoodOrderBean;
import com.wipro.food.service.Administrator;
import javax.servlet.RequestDispatcher;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/MainServlet")

public class MainServlet extends HttpServlet {

    private Administrator admin = new Administrator();
    public String addRecord(HttpServletRequest request) {
        try {
            FoodOrderBean bean = new FoodOrderBean();
            bean.setCustomerName(request.getParameter("customerName"));
            bean.setFoodItem(request.getParameter("foodItem"));
            String dateStr = request.getParameter("orderDate");
            Date orderDate = Date.valueOf(dateStr);
            bean.setOrderDate(orderDate);
            bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            bean.setPrice(Double.parseDouble(request.getParameter("price")));
            bean.setRemarks(request.getParameter("remarks"));
            Administrator admin = new Administrator();
            String result=admin.addRecord(bean);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
    public FoodOrderBean viewRecord(HttpServletRequest request) {
    	Administrator admin = new Administrator();

        String customerName = request.getParameter("customerName");
        String orderDateStr = request.getParameter("orderDate");
        Date orderDate = Date.valueOf(orderDateStr);
        return admin.viewRecord(customerName, orderDate);
    }

    public List<FoodOrderBean> viewAllRecords(HttpServletRequest request) {
    	Administrator admin = new Administrator();
    	return admin.viewAllRecords();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
           String operation=req.getParameter("operation");
           try {
           if(operation.equals("newRecord")) {
        	   String result = addRecord(req);
        	   if(result.equals("FAIL")) {
        		   resp.sendRedirect("error.html");
        	   }
        	   else {
        		   resp.sendRedirect("success.html");
        	   }
           }
           else if(operation.equals("viewRecord")) {
        	   FoodOrderBean bean = viewRecord(req);
        	   if(bean == null) {
        		   req.setAttribute("message","No matching records exists! Please try again!");
                   RequestDispatcher rd =req.getRequestDispatcher("displayFoodOrder.jsp");
                   rd.forward(req, resp);
        	   }
               else {
                   req.setAttribute("order", bean);
                   RequestDispatcher rd = req.getRequestDispatcher("displayFoodOrder.jsp");
                   rd.forward(req, resp);
               }
           }
           else if (operation.equals("viewAllRecords")) {
               List<FoodOrderBean> list = viewAllRecords(req);
               if (list == null || list.isEmpty()) {
                   req.setAttribute("message","No records available!");
                   RequestDispatcher rd =req.getRequestDispatcher("displayAllFoodOrders.jsp");
                   rd.forward(req, resp);
               }
               else {
                   req.setAttribute("orderList", list);
                   RequestDispatcher rd =req.getRequestDispatcher("displayAllFoodOrders.jsp");
                   rd.forward(req, resp);
               }
           }
           } catch (Exception e) {
               e.printStackTrace();
               resp.sendRedirect("error.html");
           }
}
}