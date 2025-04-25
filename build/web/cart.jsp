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
            <a href="products">Continue Shopping</a>
        </c:when>
        <c:otherwise>
            <form method="post" action="cart">
                <table>
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Size</th>
                            <th>Color</th>
                            <th>Unit Price</th>
                            <th>Quantity</th>
                            <th>Total Price</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="cartTotal" value="0" />
                        <c:forEach var="product" items="${sessionScope.cart}">
                            <c:set var="itemTotal" value="${product.price * product.stock}" />
                            <tr>
                                <td>${product.name}</td>
                                <td>${product.size}</td>
                                <td>${product.color}</td>
                                <td>${product.currency} ${product.price}</td>
                                <td>
                                    <input type="number" name="quantity_${product.id}" value="${product.stock}" min="1" />
                                </td>
                                <td>${product.currency} ${itemTotal}</td>
                                <td>
                                    <button type="submit" name="action" value="remove" formaction="cart?productId=${product.id}">Remove</button>
                                </td>
                            </tr>
                            <c:set var="cartTotal" value="${cartTotal + itemTotal}" />
                        </c:forEach>
                    </tbody>
                </table>
                <h3>Total: ${sessionScope.cart[0].currency} ${cartTotal}</h3>
                <button type="submit" name="action" value="update">Update Quantities</button>
                <a href="checkout.jsp">Proceed to Checkout</a>
            </form>
            <a href="products" style="display:block; margin-top: 20px;">Continue Shopping</a>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
