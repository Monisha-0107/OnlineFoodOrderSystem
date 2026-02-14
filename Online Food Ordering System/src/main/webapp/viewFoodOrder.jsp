<!DOCTYPE html>
<html>
<head>
    <title>View Food Order</title>
</head>
<body>

<h2>View Food Order</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewRecord">

    Customer Name:
    <input type="text" name="customerName" required>
    <br><br>

    Order Date:
    <input type="date" name="orderDate" required>
    <br><br>

    <input type="submit" value="ViewFoodOrder">

</form>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
