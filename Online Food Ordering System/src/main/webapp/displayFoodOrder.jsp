<%@ page import="com.wipro.food.bean.FoodOrderBean" %>

<!DOCTYPE html>
<html>
<head>
    <title>Display Food Order</title>
</head>
<body>

<h2>Food Order Details</h2>

<%
    String message = (String) request.getAttribute("message");
    FoodOrderBean bean =(FoodOrderBean) request.getAttribute("order");

    if (message != null) {
%>

        <h3 style="color:red;">
            <%= message %>
        </h3>

<%
    } 
    else if (bean != null) {
%>

        <table border="1" cellpadding="8">

            <tr>
                <th>Order ID</th>
                <td><%= bean.getOrderId() %></td>
            </tr>

            <tr>
                <th>Customer Name</th>
                <td><%= bean.getCustomerName() %></td>
            </tr>

            <tr>
                <th>Food Item</th>
                <td><%= bean.getFoodItem() %></td>
            </tr>

            <tr>
                <th>Order Date</th>
                <td><%= bean.getOrderDate() %></td>
            </tr>

            <tr>
                <th>Quantity</th>
                <td><%= bean.getQuantity() %></td>
            </tr>

            <tr>
                <th>Price</th>
                <td><%= bean.getPrice() %></td>
            </tr>

            <tr>
                <th>Remarks</th>
                <td><%= bean.getRemarks() %></td>
            </tr>

        </table>

<%
    }
%>

<br><br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
