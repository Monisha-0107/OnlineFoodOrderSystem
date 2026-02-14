<%@ page import="java.util.List" %>
<%@ page import="com.wipro.food.bean.FoodOrderBean" %>

<!DOCTYPE html>
<html>
<head>
    <title>All Food Orders</title>
</head>
<body>

<h2>All Food Orders</h2>

<%
    String message = (String) request.getAttribute("message");
    List<FoodOrderBean> list =(List<FoodOrderBean>) request.getAttribute("orderList");

    if (message != null) {
%>

        <h3 style="color:red;">
            <%= message %>
        </h3>

<%
    }
    else if (list != null && !list.isEmpty()) {
%>

        <table border="1" cellpadding="8">
            <tr>
                <th>OrderID</th>
                <th>CustomerName</th>
                <th>FoodItem</th>
                <th>OrderDate</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Remarks</th>
            </tr>

<%
        for (FoodOrderBean bean : list) {
%>
            <tr>
                <td><%= bean.getOrderId() %></td>
                <td><%= bean.getCustomerName() %></td>
                <td><%= bean.getFoodItem() %></td>
                <td><%= bean.getOrderDate() %></td>
                <td><%= bean.getQuantity() %></td>
                <td><%= bean.getPrice() %></td>
                <td><%= bean.getRemarks() %></td>
            </tr>
<%
        }
%>
        </table>

<%
    }
%>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
