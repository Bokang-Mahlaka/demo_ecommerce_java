<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utils.BCrypt" %>
<html>
<head>
    <title>Password Hasher</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h1>Password Hasher</h1>
    <form method="post" action="passwordHasher.jsp">
        <label for="password">Enter Password to Hash:</label>
        <input type="password" id="password" name="password" required />
        <button type="submit">Hash Password</button>
    </form>

    <%
        String hashedPassword = null;
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String password = request.getParameter("password");
            if (password != null && !password.isEmpty()) {
                hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            }
        }
    %>

    <c:if test="${not empty hashedPassword}">
        <h2>Hashed Password:</h2>
        <textarea rows="4" cols="80" readonly><%= hashedPassword %></textarea>
    </c:if>
</div>
</body>
</html>
