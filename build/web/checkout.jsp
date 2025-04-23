<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>TrendSphere - Checkout</title>
    <link rel="stylesheet" href="css/auth-styles.css">
</head>
<body>
<div class="container">
    <h1>Checkout</h1>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <p>Please <a href="login.jsp">login</a> to proceed with checkout.</p>
        </c:when>
        <c:otherwise>
            <form method="post" action="order">
                <h2>Shipping Information</h2>
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required />
                <br/>
                <label for="paymentMethod">Payment Method:</label>
                <select id="paymentMethod" name="paymentMethod" required>
                    <option value="creditCard">Credit Card</option>
                    <option value="paypal">PayPal</option>
                    <option value="bankTransfer">Bank Transfer</option>
                </select>
                <br/>
                <button type="submit">Place Order</button>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
