<!DOCTYPE html>
<html>
<head>
    <title>Add Food Order</title>
</head>
<body>

<h2>Add Food Order</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="newRecord">

    Customer Name:
    <input type="text" name="customerName" required>
    <br><br>

    Food Item:
    <input type="text" name="foodItem" required>
    <br><br>

    Order Date:
    <input type="date" name="orderDate" required>
    <br><br>

    Quantity:
    <input type="number" name="quantity" required>
    <br><br>b

    Price:
    <input type="text" name="price" required>
    <br><br>

    Remarks:
    <input type="text" name="remarks">
    <br><br>

    <input type="submit" value="AddFoodOrder">

</form>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
