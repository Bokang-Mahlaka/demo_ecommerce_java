<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>TrendSphere - Order Confirmation</title>
    <link rel="stylesheet" href="css/auth-styles.css">
</head>
<body>
<div class="container">
    <h1>Order Confirmation</h1>
    <p>Thank you for your purchase! Your order has been placed successfully.</p>

    <h2>Order Details</h2>
    <p>Order ID: ${order.orderId}</p>
    <p>Order Date: ${order.orderDate}</p>
    <p>Total Price: ${order.totalPrice}</p>

    <h3>Items:</h3>
    <ul>
        <c:forEach var="item" items="${order.orderItems}">
            <li>Product ID: ${item.productId}, Quantity: ${item.quantity}, Price: ${item.price}</li>
        </c:forEach>
    </ul>

    <h2>Invoice</h2>
    <p>Invoice ID: ${invoice.invoiceId}</p>
    <p>Invoice Date: ${invoice.invoiceDate}</p>
    <p>Total Amount: ${invoice.totalAmount} ${invoice.currency}</p>

    <p>An invoice has been sent to your email address.</p>
    <a href="index.jsp">Return to Home</a>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
