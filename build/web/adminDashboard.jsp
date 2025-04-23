<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Admin Dashboard - TrendSphere</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h1>Admin Dashboard</h1>
    <p>Welcome, Admin! Use the links below to manage the platform:</p>
    <ul>
        <li><a href="adminProductManagement.jsp">Manage Products</a></li>
        <!-- Add more admin links here as needed -->
        <li><a href="inventoryManagement.jsp">Manage Inventory</a></li>
        <li><a href="orderManagement.jsp">Manage Orders</a></li>
        <li><a href="userManagement.jsp">Manage Users</a></li>
    </ul>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
