<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Product Catalog</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h1>Product Catalog</h1>

    <c:choose>
        <c:when test="${empty products}">
            <p>No products available.</p>
        </c:when>
        <c:otherwise>
            <div class="product-list">
                <c:forEach var="product" items="${products}">
                    <div class="product-item">
                        <img src="${product.imageUrl}" alt="${product.name}" class="product-image" />
                        <h2>${product.name}</h2>
                        <p>Type: ${product.type}</p>
                        <p>Size: ${product.size}</p>
                        <p>Color: ${product.color}</p>
                        <p>Price: ${product.price} ${product.currency}</p>
                        <p>Stock: ${product.stock}</p>
                        <p>Eco Friendly: <c:choose>
                            <c:when test="${product.ecoFriendly}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose></p>
                        <form method="post" action="cart">
                            <input type="hidden" name="action" value="add" />
                            <input type="hidden" name="productId" value="${product.id}" />
                            <button type="submit">Add to Cart</button>
                        </form>
                        <form method="post" action="order" style="margin-top:10px;">
                            <input type="hidden" name="productId" value="${product.id}" />
                            <input type="hidden" name="quantity" value="1" />
                            <button type="submit">Buy Now</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
