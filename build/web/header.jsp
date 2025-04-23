<%@page import="model.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
    User user = (User) session.getAttribute("user");
%>

<style>
    :root {
        --primary: #2c3e50;
        --secondary: #34495e;
        --accent: #000000;
        --text-light: #7f8c8d;
    }
    
    .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }
    
    header {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    
    .header-container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }
    
    .logo {
        text-align: center;
        margin-bottom: 20px;
    }
    
    .logo-text {
        font-size: 2rem;
        font-weight: 700;
        color: var(--primary);
        text-decoration: none;
    }
    
    nav {
        background-color: white;
        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        border-radius: 8px;
        padding: 15px;
        margin-bottom: 40px;
    }
    
    nav ul {
        display: flex;
        justify-content: center;
        list-style: none;
        padding: 0;
        margin: 0;
        flex-wrap: wrap;
    }
    
    nav li {
        margin: 0 15px;
    }
    
    nav a {
        text-decoration: none;
        color: var(--secondary);
        font-weight: 600;
        font-size: 1rem;
        padding: 8px 12px;
        border-radius: 4px;
        transition: all 0.3s ease;
    }
    
    nav a:hover {
        color: var(--accent);
        background-color: rgba(0, 0, 0, 0.05);
    }
    
    .welcome-text {
        color: var(--secondary);
        font-weight: 500;
        padding: 8px 12px;
    }
    
    @media (max-width: 768px) {
        nav ul {
            flex-direction: column;
            align-items: center;
        }
        
        nav li {
            margin: 8px 0;
        }
    }
</style>

<header>
    <div class="header-container">
        <div class="logo">
            <a href="index.jsp" class="logo-text">TrendSphere</a>
        </div>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="products">Shop</a></li>               
                <li><a href="cart.jsp">Cart</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li class="welcome-text">Welcome, ${sessionScope.user.username}</li>
                        <li><a href="user?action=logout">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="login.jsp">Login</a></li>
                        <li><a href="register.jsp">Register</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
</header>