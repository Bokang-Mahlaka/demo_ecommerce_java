<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Admin - Product Management</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h1>Admin Product Management</h1>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${param.success == 'true'}">
        <p class="success">Product added successfully!</p>
    </c:if>

    <form method="post" action="products" enctype="multipart/form-data">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required />

        <label for="type">Type:</label>
        <input type="text" id="type" name="type" required />

        <label for="size">Size:</label>
        <input type="text" id="size" name="size" required />

        <label for="color">Color:</label>
        <input type="text" id="color" name="color" required />

        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" required />

        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" required />

        <label for="currency">Currency:</label>
<select id="currency" name="currency" required>
<option value="">Select Currency</option>
<%
dao.CurrencyDAO currencyDAO = new dao.CurrencyDAO();
java.util.List<model.Currency> currencies = null;
try {
currencies = currencyDAO.getAllCurrencies();
} catch (Exception e) {
out.println("<p>Error loading currencies: " + e.getMessage() + "</p>");
}
if (currencies != null) {
for (model.Currency currency : currencies) {
%>
<option value="<%= currency.getCode() %>"><%= currency.getCode() %> - <%= currency.getSymbol() %></option>
<%
}
}
%>
</select>

        <label for="imageFile">Product Image:</label>
        <input type="file" id="imageFile" name="imageFile" accept="image/*" />

        <label for="ecoFriendly">Eco Friendly:</label>
        <select id="ecoFriendly" name="ecoFriendly">
            <option value="true">Yes</option>
            <option value="false" selected>No</option>
        </select>

        <button type="submit">Add Product</button>
    </form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
