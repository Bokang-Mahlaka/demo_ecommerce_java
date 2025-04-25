<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="header.jsp" %>--%>
    <%@ page import="model.User" %>

<html>
<head>
    <title>Admin Dashboard | TrendSphere</title>
    <link rel="stylesheet" href="css/styles.css">
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --accent: #e74c3c;
            --light: #ecf0f1;
            --dark: #2c3e50;
            --text: #333;
            --text-light: #7f8c8d;
            --success: #27ae60;
            --warning: #f39c12;
            --info: #3498db;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            color: var(--text);
        }
        
        .admin-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 30px 20px;
        }
        
        .admin-header {
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 1px solid var(--light);
        }
        
        .admin-header h1 {
            color: var(--primary);
            font-size: 2.2rem;
            margin-bottom: 10px;
        }
        
        .admin-header p {
            color: var(--text-light);
            font-size: 1.1rem;
        }
        
        .dashboard-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 25px;
            margin-top: 30px;
        }
        
        .dashboard-card {
            background: white;
            border-radius: 8px;
            padding: 25px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.05);
            transition: transform 0.3s, box-shadow 0.3s;
            border-top: 4px solid var(--accent);
            text-align: center;
        }
        
        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }
        
        .dashboard-card h2 {
            color: var(--primary);
            margin-top: 0;
            margin-bottom: 15px;
            font-size: 1.3rem;
        }
        
        .dashboard-card p {
            color: var(--text-light);
            margin-bottom: 20px;
            font-size: 0.95rem;
        }
        
        .dashboard-card a {
            display: inline-block;
            padding: 10px 20px;
            background-color: var(--accent);
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-weight: 600;
            transition: background-color 0.3s;
        }
        
        .dashboard-card a:hover {
            background-color: #c0392b;
        }
        
        /* Different card colors */
        .card-products {
            border-top-color: var(--info);
        }
        
        .card-products a {
            background-color: var(--info);
        }
        
        .card-products a:hover {
            background-color: #2980b9;
        }
        
        .card-inventory {
            border-top-color: var(--warning);
        }
        
        .card-inventory a {
            background-color: var(--warning);
        }
        
        .card-inventory a:hover {
            background-color: #e67e22;
        }
        
        .card-users {
            border-top-color: var(--success);
        }
        
        .card-users a {
            background-color: var(--success);
        }
        
        .card-users a:hover {
            background-color: #219653;
        }
        
        @media (max-width: 768px) {
            .dashboard-grid {
                grid-template-columns: 1fr;
            }
            
            .admin-header h1 {
                font-size: 1.8rem;
            }
        }
    </style>
</head>


<body>
    <header>
        <nav>
            <ul>
                <li><a href="user?action=logout">Logout</a></li>
              
            </ul>
        </nav>
    </header>

<div class="admin-container">
    <div class="admin-header">
        <h1>Admin Dashboard</h1>
        <p>Welcome back! Manage your TrendSphere platform efficiently.</p>
    </div>
    
    <div class="dashboard-grid">
        <div class="dashboard-card card-products">
            <h2>Product Management</h2>
            <p>Add, edit, or remove products from your catalog</p>
            <a href="adminProductManagement.jsp">Manage Products</a>
        </div>
        
        <div class="dashboard-card card-inventory">
            <h2>Inventory Management</h2>
            <p>Track and update product stock levels</p>
            <a href="inventoryManagement.jsp">Manage Inventory</a>
        </div>
        
        <div class="dashboard-card">
            <h2>Order Management</h2>
            <p>View and process customer orders</p>
            <a href="admin/orders">Manage Orders</a>
        </div>
        
        
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>