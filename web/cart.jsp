<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>TrendSphere - Shopping Cart</title>
    <link rel="stylesheet" href="css/auth-styles.css">
</head>
<body>
<div class="container">
    <h1>Your Shopping Cart</h1>
    <c:choose>
        <c:when test="${empty sessionScope.cart}">
            <p>Your cart is empty.</p>
            <a href="productCatalog.jsp">Continue Shopping</a>
        </c:when>
        <c:otherwise>
            <form method="post" action="cart">
                <table>
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Size</th>
                            <th>Color</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${sessionScope.cart}">
                            <tr>
                                <td>${product.name}</td>
                                <td>${product.size}</td>
                                <td>${product.color}</td>
                                <td>${product.currency} ${product.price}</td>
                                <td>
                                    <input type="number" name="quantity_${product.id}" value="${product.stock}" min="1" />
                                </td>
                                <td>
                                    <button type="submit" name="action" value="remove" formaction="cart?productId=${product.id}">Remove</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" name="action" value="update">Update Quantities</button>
                <a href="checkout.jsp">Proceed to Checkout</a>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
